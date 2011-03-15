package weibo.xiaolong;

import java.util.SortedSet;

import oauth.signpost.OAuthProvider;
import oauth.signpost.basic.DefaultOAuthProvider;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.exception.OAuthNotAuthorizedException;
import weibo.constant.Constant;
import weibo.user_interface.Users_show;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

/**
 * @author 王晓龙
 * 
 */


public class AuthActivity extends Activity{
	
	CommonsHttpOAuthConsumer httpOauthConsumer;
	OAuthProvider httpOauthprovider;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oauthlayout);
        //APP KEY和APP Secret
        String consumerKey="358621552";
        String consumerSecret="75436f1393b5308ba2348c77a9567b61";
        String callBackUrl="myapp://AuthActivity";
       
        try{
        	httpOauthConsumer = new CommonsHttpOAuthConsumer(consumerKey,consumerSecret);
    		httpOauthprovider = new DefaultOAuthProvider("http://api.t.sina.com.cn/oauth/request_token","http://api.t.sina.com.cn/oauth/access_token","http://api.t.sina.com.cn/oauth/authorize");
    		String authUrl = httpOauthprovider.retrieveRequestToken(httpOauthConsumer, callBackUrl);
    		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(authUrl)));   		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
	
	@Override
    protected void onNewIntent(Intent intent) {
    	super.onNewIntent(intent);
    	Uri uri = intent.getData();
    	String verifier = uri.getQueryParameter(oauth.signpost.OAuth.OAUTH_VERIFIER);
    	try {
            httpOauthprovider.setOAuth10a(true); 
            httpOauthprovider.retrieveAccessToken(httpOauthConsumer,verifier);
        } catch (OAuthMessageSignerException ex) {
            ex.printStackTrace();
        } catch (OAuthNotAuthorizedException ex) {
            ex.printStackTrace();
        } catch (OAuthExpectationFailedException ex) {
            ex.printStackTrace();
        } catch (OAuthCommunicationException ex) {
            ex.printStackTrace();
        }
        SortedSet<String> user_id= httpOauthprovider.getResponseParameters().get("user_id");
        
        String userId=user_id.first();
        String userKey = httpOauthConsumer.getToken();
        String userSecret = httpOauthConsumer.getTokenSecret();
        Constant.user_id =userId;
        Constant.userKey=userKey;
        Constant.userSecret=userSecret;
              
        TextView text=(TextView)findViewById(R.id.text);
        text.setText("suerId:"+userId+"/userKey:"+userKey+"/userSecret:"+userSecret);
        
        
        //测试Users_show的工作情况
        Users_show us =new Users_show();
        
        //测试......的工作情况
        
        
        
        
    }
}
