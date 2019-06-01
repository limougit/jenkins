package com.imooc.fileupload.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
@Service
public class ScheduleService {
    @Scheduled(cron = "0/5 * * * * ?")
    public void job(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(new Date());
        System.out.println("=========" + dateStr);
    }
}
