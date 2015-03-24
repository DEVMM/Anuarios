package br.com.gmm.anuariosviews.scheduler;

import java.text.ParseException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import br.com.gmm.anuariosviews.views.Views;

public class SchedulerAgent extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4867136433906642551L;
	private static final Logger LOGGER = Logger.getLogger(SchedulerAgent.class.getName());
	public static Scheduler scheduler;


	@Override
	public void init(ServletConfig config) throws ServletException {		
		try {
			LOGGER.info("iniciando servletAgente ViewsAnuarios");
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.start();
			JobDetail jobDetail = new JobDetail("MyJob", Scheduler.DEFAULT_GROUP, Views.class);
			CronTrigger trigger = new CronTrigger("MyTrigger", Scheduler.DEFAULT_GROUP, "0 0 17 ? * FRI");
//			CronTrigger trigger = new CronTrigger("MyTrigger", Scheduler.DEFAULT_GROUP, "0/50 * * * * ?");
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (SchedulerException e ) {
			LOGGER.warn(e);
		}
		catch (ParseException e){
			LOGGER.warn(e);
		}
	}


}
