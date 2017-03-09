package aromacafe.com.aromacafebl.extras;

import android.text.Html;

/**
 * Created by m.jagodic on 26.7.2016..
 */
public class StripHtml {

    public String StripHtml(String html) {
        return Html.fromHtml(html).toString();
    }
}
