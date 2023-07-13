import java.time.ZonedDateTime;

public class Test {
    public static void main(String[] args) {
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now);//获得yml中predicates中部分匹配条件需要的时间格式
        //2022-02-16T18:06:45.103663+08:00[Asia/Shanghai]
    }
}
