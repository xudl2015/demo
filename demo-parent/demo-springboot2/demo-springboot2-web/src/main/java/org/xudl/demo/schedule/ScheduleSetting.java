package org.xudl.demo.schedule;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component // 使得当前配置类生效
@EnableScheduling // 开启定时任务功能
public class ScheduleSetting {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${demo.schedule.cron}")
	private String scheduleTaskCron;

	@PostConstruct
	public void init() {
		logger.info("scheduleTaskCron:" + scheduleTaskCron);
	}

	/**
	 * 设置定时任务
	 */
	@Scheduled(cron = "${demo.schedule.cron}")
	public void scheduleTask() {
		logger.info("-----scheduleTask----");
	}
}
