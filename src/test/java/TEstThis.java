import org.junit.*;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 * Created by numannsaid on 14/06/16.
 */
public class TEstThis {

    @Test
    public void testThis() {
        StringBuilder sb = new StringBuilder("start");
        sb.insert(0, "le");
        System.out.println(sb);

        String s = "start";
        System.out.println(s.substring(4));

        String n = "numann";
        String res = "";
        for(int i=n.length()-1; i > -1; i--) {
            res = res + n.charAt(i);
        }
        System.out.println(res);
    }

}
