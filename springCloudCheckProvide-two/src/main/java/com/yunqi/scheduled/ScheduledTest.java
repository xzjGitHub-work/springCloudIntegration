package com.yunqi.scheduled;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;

/**
 * java类作用描述
 *
 * @author xuzhaoju
 * @createDate 2021/2/18 19:55
 */
@Component
public class ScheduledTest {
//    @Scheduled(fixedRate = 3000)
//    public void ScheduledTestDemo(){
//        System.out.println("运行了"+ DateFormatUtils.format(Calendar.getInstance().getTime(),"yyyy-MM-dd"));
//    }

    public static void main(String[] args) {
        System.out.println(DateFormatUtils.format(Calendar.getInstance().getTime(),"yyyy-MM-dd"));
    }
}
