package com.etrans.mina.client.imp;

import java.nio.charset.Charset;
import javax.jms.JMSException;

import org.apache.log4j.Logger;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;

import com.etrans.common.netbase.mina.MinaClient;
import com.etrans.common.netbase.mq.MQServer;
import com.etrans.mina.MinaConfigEntity;
import com.etrans.mina.client.handler.MinaClientHandler;

/**
 * Mina�ͻ���,��MSC����켣����
 * 
 * @author Pomelo(����.)
 * @since 2012-12-10 10:42
 * @version 1.0
 */
public class MinaClientImp extends MinaClient {
	
	/** ���ն�*/
	private MinaClientHandler clientHandler;
	
	/** ��־����*/
	private static Logger logger = Logger.getLogger(MinaClientImp.class);
	
	/** ������*/
	private TextLineCodecFactory lineCodec;
	
	{
		lineCodec=new TextLineCodecFactory(
				Charset.forName("UTF-8"), 
				LineDelimiter.WINDOWS.getValue(),
				LineDelimiter.WINDOWS.getValue()
		);
		lineCodec.setDecoderMaxLineLength(1024*1024); 
		lineCodec.setEncoderMaxLineLength(1024*1024);
	}
	
	/**
	 * ���캯������ɻ�������������
	 *    
	 * @author Pomelo(����.)
	 * @throws JMSException 
	 */
	public MinaClientImp(MinaConfigEntity minaConfigEntity,MQServer mq) throws JMSException{
		super(minaConfigEntity);
		super.setClientHandler(clientHandler = new MinaClientHandler(mq));
		super.setClientFilterAdapter(new ProtocolCodecFilter(lineCodec));
		new NoopThread().start();		
	}
	
	/**
	 * getClientHandler
	 *  
	 * @return  clientHandler TrackClientHandler
	 */
	public MinaClientHandler getClientHandler() {
		return clientHandler;
	}

	/**
	 * ����������д����
	 * 
	 * @param object:Object
	 */
	@Override
	public void write(Object obect) {
		super.session.write(obect);
	}
	
	/**
	 * �����߳�
	 * 
	 * @author Pomelo(����.)
	 * @since 2012-12-14 15:20
	 * @version 1.0
	 */
	class NoopThread extends Thread{

		/**
		 * ��������߳�
		 */
		@Override
		public void run() {
			while(true){
				try {
					// ��5����Ϊ�����MSC����һ������
					Thread.sleep(30000);
					write("##1,9,12:12:12,NOOP");
				} catch (InterruptedException e) {
					logger.error(e.getMessage());
				}
			}
		}
		
	}
	
}
