package com.ccut.shixun.controller;

import com.ccut.shixun.dto.AccessTokenDTO;
import com.ccut.shixun.dto.GithubUser;
import com.ccut.shixun.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 负责接收github给callback回来的内容
 */
@Controller
public class AuthorizeController {

    @Autowired  //自动把spring容器里写好的实例化的实例加载到要使用的上下文中
    private GithubProvider githubProvider;

    @Value("${github.client.id}")  //配置文件里读key是github.client.id的value，把它赋值到client_id
    private String clientId;

   @Value("${github.client.secret}")   //配置文件里读key是github.client_secret的value，把它赋值到client_secret
    private String clientSecret;

    @Value("${github.redirect.uri}") //配置文件里读key是github..redirect_uri的value，把它赋值到redirect_uri
    //private String redirectUri = "http://localhost:8887/callback";
    private String redirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,  //请求参数接收网站的code
                           @RequestParam(name = "state") String state){  //请求参数接收网站的state
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        //accessTokenDTO.setRedirect_uri("http://localhost:8887/callback");
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index"; //返回到index界面
    }
}
