<?xml version="1.0" encoding="UTF-8"?>
<WebElementEntity>
   <description></description>
   <name>section_OSG</name>
   <tag></tag>
   <elementGuidId>fc4745e1-47cd-44b4-9d63-70d7a70d6717</elementGuidId>
   <selectorCollection>
      <entry>
         <key>XPATH</key>
         <value>//div[@id='body']/section</value>
      </entry>
      <entry>
         <key>CSS</key>
         <value>section.featured</value>
      </entry>
   </selectorCollection>
   <selectorMethod>XPATH</selectorMethod>
   <useRalativeImagePath>true</useRalativeImagePath>
   <webElementProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>tag</name>
      <type>Main</type>
      <value>section</value>
   </webElementProperties>
   <webElementProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>class</name>
      <type>Main</type>
      <value>featured</value>
   </webElementProperties>
   <webElementProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>text</name>
      <type>Main</type>
      <value>

        

            
                
                    
                        
                            
                        
                        
                            メールアドレスサインイン
                            メールアドレス形式のアカウントとパスワードを入力してサインインします。
                        
                    
                

                
                    
                        
                            
                        
                        
                            OSG アカウントサインイン
                            Online Service Gate のアカウントとパスワードを入力してサインインします。
                        
                    
                

                
                    
                        
                            
                        
                        
                            パスワードレスサインイン
                            メールアドレス形式のアカウントとクライアント証明書を使用してサインインします。
                        
                    
                

                
                    
                        
                            
                        
                        
                            設定
                            Online Service Gate の設定を変更します。
                        
                    
                

            
            
                メールアドレスサインイン
                
                アカウント
                
                パスワード
                
                
                    
                    
                

                パスワードを変更   

            
            
                
                
                
           
            
                OSG アカウントサインイン
                
                    サービスコード
                    
                    アカウント
                    
                    パスワード
                    
                    
                        
                        
                    
                
                パスワードを変更
            
            
                
                
                
           
            
                パスワードレスサインイン

                メールアドレス
                
                
                    
                    
                

                サインインをクリック後、証明書選択画面が表示された場合、ご利用になる証明書を選択してください。
            
            
                
                
                
           
            
                設定
                
                     次回表示時に、最後に使用したサインイン方法を選択する

                
                 
                デバイスIDダウンロード
                
                    デバイスIDダウンロード
                
                 
                バージョン情報
                
                    製品名：Online Service Gate® Web
                    バージョン：2.3.60.143
                
            

            
                $().ready(function () {
                    //return;
                    $(&quot;.sidecontent&quot;).css(&quot;display&quot;, &quot;none&quot;).css(&quot;margin-bottom&quot;, &quot;0&quot;);
                    $(&quot;.button&quot;).css(&quot;display&quot;, &quot;inline&quot;);


                    var c = function () {
                        $(&quot;.tileBlock&quot;).css(&quot;width&quot;, &quot;450px&quot;);
                        $(&quot;.tileDescription&quot;).css(&quot;display&quot;, &quot;block&quot;);
                        $(&quot;.sidecontent&quot;).css(&quot;display&quot;, &quot;none&quot;);
                        $(&quot;.tile&quot;).css(&quot;opacity&quot;, &quot;1&quot;);
                    }
                    $(&quot;.cancelButton&quot;).click(c);

                    var f = function (name) {
                        $(&quot;#&quot; + name + &quot;Link&quot;).click(function () {
                            c();
                            $(&quot;.tiles&quot;).css(&quot;float&quot;, &quot;left&quot;);
                            $(&quot;.tile&quot;).css(&quot;opacity&quot;, &quot;.4&quot;);
                            $(&quot;#&quot; + name + &quot;Tile&quot;).css(&quot;opacity&quot;, &quot;1&quot;);
                            $(&quot;.tileDescription&quot;).css(&quot;display&quot;, &quot;none&quot;);
                            $(&quot;.tileBlock&quot;).css(&quot;width&quot;, &quot;70px&quot;);
                            $(&quot;#&quot; + name).css(&quot;display&quot;, &quot;block&quot;);
                            return false;
                        });
                    }
                    f(&quot;mailSignIn&quot;);
                    f(&quot;osgSignIn&quot;);
                    f(&quot;passwordlessSignIn&quot;);
                    f(&quot;settings&quot;);

                        
                        $(&quot;#passwordlessSignInLink&quot;).click(function () {
                            if ($(&quot;#PasswordlessUpn&quot;).val())
                            {
                                $(&quot;#passwordlessSignInSubmit&quot;).click();
                            }
                        });
                        

                        $(&quot;#mailSignInLink&quot;).click();


                    // 次回表示時に、最後に使用したサインイン方法を選択する 
                    $(&quot;#keepSignin&quot;).click(function () {
                        var checkstate = $(&quot;#keepSignin&quot;).prop(&quot;checked&quot;);
                        $(&quot;[id=RememberAuthWay]&quot;).each(function (index, element) {
                            this.checked = checkstate;
                        });
                    });

                });
            

        
        
    </value>
   </webElementProperties>
   <webElementProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>xpath</name>
      <type>Main</type>
      <value>id(&quot;body&quot;)/section[@class=&quot;featured&quot;]</value>
   </webElementProperties>
   <webElementXpaths>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>xpath:idRelative</name>
      <type>Main</type>
      <value>//div[@id='body']/section</value>
   </webElementXpaths>
   <webElementXpaths>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>xpath:position</name>
      <type>Main</type>
      <value>//section</value>
   </webElementXpaths>
</WebElementEntity>
