package br.com.gmm.anuariosviews.session;
import java.io.File;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.com.gmm.core.exception.GmmHibernateException;
import br.com.gmm.core.util.GmmConstants;
import br.com.gmm.session.GMMSessionFactory;
public class HibernateSessionFactory {
	private static Logger log = Logger.getLogger(HibernateSessionFactory.class);
	private static SessionFactory sessionFactoryAnuariosViews;

	static {

		rebuildSessionFactoryAnuariosViews();
		
	}


	public static Session getSessionAnuariosViews() {
		Session session = null;

		try { 

			session = sessionFactoryAnuariosViews.getCurrentSession();

		} catch (GmmHibernateException e) {

			log.info("Abrindo nova sessão: "+ e.getMessage());

			session = sessionFactoryAnuariosViews.openSession();

		}

		if (session == null || !session.isOpen()) {

			session = sessionFactoryAnuariosViews.openSession();

		}
		session.beginTransaction();
		return session;

	}
	
	public static void rebuildSessionFactoryAnuariosViews() {
		GMMSessionFactory gsf = new GMMSessionFactory(GmmConstants.POOL_NAME_ANUARIOS_VIEWS,  getPoolUrl()+GmmConstants.PATH_POOL);
		sessionFactoryAnuariosViews = gsf.getSessionFactory();
//		gsf.exportScriptSqlCreate("docs\\sql\\base_core.sql");
		
	}
	
	public static void closeSession(Session session){
		if(null!=session && session.isOpen()){
			session.flush();
			session.getTransaction().commit();
			if (session.isOpen()) {
				session.close();
			}			
		}
			
	}
	public static void main(String[] args) {
		Map<String, String> envs = System.getenv();  
		for (String key : envs.keySet())  
		    System.out.println(key + ": " + envs.get(key));  
		// ou  
		
		String pool = System.getenv("SystemDrive")+File.separatorChar+"GMM"+File.separatorChar+"pool"+File.separatorChar;
		System.out.println("koto->"+getPoolUrl());
		log.info("dir-> "+pool);
	}
	
	private static String getPoolUrl(){
		String pool = File.separatorChar+"GMM"+File.separatorChar+"pool"+File.separatorChar;
		System.out.println(pool);
		log.info("dir-> "+pool);
		return pool;		
	}

}


