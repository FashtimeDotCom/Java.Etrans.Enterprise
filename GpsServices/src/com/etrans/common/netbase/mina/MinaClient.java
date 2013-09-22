package com.etrans.common.netbase.mina;

import java.net.InetSocketAddress;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;

import org.apache.log4j.Logger;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.executor.OrderedThreadPoolExecutor;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.etrans.common.MQDCConfigUtil;
import com.etrans.mina.MinaConfigEntity;
import com.etrans.system.control.MQDCCheckMonitor;
import com.etrans.system.control.mqdcswitch.MqdcSwitch;

/**
 * Mina�ͻ���
 * 
 * @author Pomelo(����.)
 * @since 2012-12-10 10:42
 * @version 1.0
 */
public abstract class MinaClient {
		
	/** �������� */
	protected IoConnector connector = null;
	
	/** ��־����*/
	private static Logger logger = Logger.getLogger(MinaClient.class);
	
	/** IO�Ự */
	protected IoSession session = null;
	
	/** �ͻ���ҵ���߼������� */
	private MinaBaseClientHandler clientHandler;
	
	/** Mina������Ϣ*/
	protected MinaConfigEntity minaConfigEntity;

	/** ���˱��빤�� */
	private IoFilterAdapter clientFilterAdapter;
	
	/** ͳ������MSC����*/
	private long countMinaReConnect = 0l;
	
	/** ͳһ�����쳣����*/
	private long countMinaReConnectException = 0l;
	
	/**
	 * ���캯��
	 * 
	 * @param minaConfigEntity MinaConfigEntity
	 */
	public MinaClient(MinaConfigEntity minaConfigEntity){
		this.setMinaConfigEntity(minaConfigEntity);
		minaClientInit();
	}
	
	/**
	 * <title>
	 * Mina�ͻ��˳�ʼ��
	 * <title> 
	 * minaClientInit
	 * <p>
	 * �������������С����ó�ʱ������ҵ����������ñ�����
	 * </p>
	 */
	@SuppressWarnings("deprecation")
	public void minaClientInit(){
		/* ����һ���������Ŀͻ��˳��� */
		connector = new NioSocketConnector();
		/** Ĭ�ϳ�ʱʱ�� */
		connector.setConnectTimeout(MQDCConfigUtil.MINA_CONNECT_TIMEOUT);		
	}
	
	/**
	 * ���ҵ���߼���������
	 * 
	 * @param hander IoHandlerAdapter
	 */
	public void setClientHandler(MinaBaseClientHandler clientHandler) {
		this.clientHandler = clientHandler;
	}
	
	/**
	 * ��ȡҵ���߼���������
	 * 
	 * @return hander IoHandlerAdapter
	 */
	public MinaBaseClientHandler getClientHandler() {
		return clientHandler;
	}

	/**
	 * ���˱��빤��
	 *  
	 * @param clientFilterAdapter  IoFilterAdapter
	 */
	public void setClientFilterAdapter(IoFilterAdapter clientFilterAdapter) {
		this.clientFilterAdapter = clientFilterAdapter;
	}
	
	/**
	 * ��ȡMina����
	 * 
	 * @return minaConfigEntity MinaConfigEntity
	 */
	public MinaConfigEntity getMinaConfigEntity() {
		return minaConfigEntity;
	}

	/**
	 * ����Mina
	 * <code>setMinaConfigEntity</code>
	 *  
	 * @param minaConfigEntity MinaConfigEntity
	 */
	public void setMinaConfigEntity(MinaConfigEntity minaConfigEntity) {
		this.minaConfigEntity = minaConfigEntity;
	}
	
	/**
	 * ���������˽�������
	 * <p>
	 *  ���߳̽��մ���
	 *  ��ȡ������Ϣ��Ϊ���Ӷ���
	 *  ��MSD�以�����ţ�����Ӱ�쵽����MSD�Ľ�������
	 * </p>
	 * 
	 * @throws Exception  connectionServerException
	 */
	public void connectionServer() throws Exception{		
		try {
			ExecutorService filterExecutor = new OrderedThreadPoolExecutor(
					MQDCConfigUtil.MINA_RECEIVE_THREAD_MIN, 
					MQDCConfigUtil.MINA_RECEIVE_THREAD_MAX
			);// ���߳�
			{
				connector.getFilterChain().addLast("codec", clientFilterAdapter);
				connector.getFilterChain().addLast("threadPool", new ExecutorFilter(filterExecutor));
				connector.setHandler(clientHandler);
				connector.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, MQDCConfigUtil.MINA_IDLETIME);  
				connector.setDefaultRemoteAddress(
						new InetSocketAddress(
								minaConfigEntity.getTunnelIp(), 
								Integer.parseInt(minaConfigEntity.getTunnelPort())
						)
				);
			}
			{
				ConnectFuture future = connector.connect(
						new InetSocketAddress(
								minaConfigEntity.getTunnelIp(), 
								Integer.parseInt(minaConfigEntity.getTunnelPort())
						)
				);// ��������
				future.awaitUninterruptibly();// �ȴ����Ӵ������
				session = future.getSession();// ���session
			}
			MqdcSwitch.setMscIslink(true);
			scheduleMonitor();
		} catch (Exception e) {
			MqdcSwitch.setMscIslink(false);
			throw new Exception("���ӵ�������ַΪ��"+minaConfigEntity.getTunnelIp()+"��" +
					"�˿�Ϊ��"+ minaConfigEntity.getTunnelPort()+"���ķ�������ʧ�ܣ���˶Բ�����"+e.getMessage());
		}	 
	}
	
	/**
	 * �̶�ʱ��ִ��һ�����Ӽ�� 
	 * 
	 * �÷������Զ�����
	 */
	private void scheduleMonitor() {
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				if (null != connector && !connector.isActive()) {
					interruptConnect();
					restarConnect();
				}
			}
		}, 10000, MQDCConfigUtil.MINA_RECON_MSC_TIME * 1000);
	}
	
	/**
	 * ����MSC
	 * <P>
	 * �Զ���⣬�Զ�����
	 * <p>
	 */
	public void restarConnect(){
		try {
			MQDCCheckMonitor.ISRESUBSCRI = true;
			countMinaReConnect++;
			logger.info("��������MSC��"+minaConfigEntity.getTunnelIp()+":"+minaConfigEntity.getTunnelPort()+"�����Ժ�");					
			ConnectFuture connFuture = connector.connect();
			connFuture.awaitUninterruptibly();
			session = connFuture.getSession();
			logger.info("�����ɹ�����"+minaConfigEntity.getTunnelIp()+":"+minaConfigEntity.getTunnelPort()+"��");
			MqdcSwitch.setMscIslink(true);
			logger.error("��ǰ�ܹ���������MSC��"+countMinaReConnect+"����");
		} catch (Exception e) {
			MqdcSwitch.setMscIslink(false);
			countMinaReConnectException++;
			logger.error("�����쳣��"+minaConfigEntity.getTunnelIp()+":" +
					""+minaConfigEntity.getTunnelPort()+"��"+e.getMessage());
			logger.error("����MSC��"+minaConfigEntity.getTunnelIp()+":"+minaConfigEntity.getTunnelPort()+
					"���쳣������"+countMinaReConnectException+"����");
		}		
	}
	
	/**
	 * �Ͽ�����
	 */
	public void interruptConnect(){
		try {
			/* �ȴ����ӶϿ� */
			if(session!=null){
				session.getCloseFuture().awaitUninterruptibly();
				session.close(true);
			}
		} catch (Exception e) {
			logger.error("�ر����ӡ�"+minaConfigEntity.getTunnelIp()+":"
					+minaConfigEntity.getTunnelPort()+"���쳣:"+e.getMessage());
		}
	}

	/**
	 * ��д����
	 * 
	 * @param obect
	 */
	public abstract void write(Object obect);
}
