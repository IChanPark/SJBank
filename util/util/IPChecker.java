package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class IPChecker{
	
	private IPChecker() {
	}
	
	private static class Holder {
        public static final IPChecker IP = new IPChecker();
    }
	
	public static IPChecker getInstance() {
        return Holder.IP;
    }
	
	private String jsonReadAll(Reader reader) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		
		while ((cp = reader.read()) != -1)
			sb.append((char) cp);

		return sb.toString();
	}

	private String readJsonFromUrl(String ip) {
		//API에서 JSON파일로 받아서 그걸 다시 Gson을 이용해 원하는 값만 가져옴
		InputStream is = null;
		String code = null; 
		try {
			is = new URL("http://whois.kisa.or.kr/openapi/ipascc.jsp?query="+ip+
					"&key=2019092822214292850855&answer=json").openStream();

			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

			String jsonText = jsonReadAll(rd);

			JsonParser jsonParser = new JsonParser();
			JsonElement jsonElement = jsonParser.parse(jsonText);

			code = jsonElement.getAsJsonObject().get("whois").getAsJsonObject().get("countryCode").toString();
			code = code.substring(1, code.length()-1);
		} catch (Exception e) {
		} finally { try {is.close();} catch (IOException e) {} }
		return code;
	}
	
	public String search(String ip) {
		return readJsonFromUrl(ip);
	}
}

