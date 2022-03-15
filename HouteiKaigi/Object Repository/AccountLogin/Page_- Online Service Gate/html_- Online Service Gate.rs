<?xml version="1.0" encoding="UTF-8"?>
<WebElementEntity>
   <description></description>
   <name>html_- Online Service Gate</name>
   <tag></tag>
   <elementGuidId>4e3383dc-3c4f-40ed-a8c7-0d8169bcefe6</elementGuidId>
   <selectorCollection>
      <entry>
         <key>XPATH</key>
         <value>//*/text()[normalize-space(.)='']/parent::*</value>
      </entry>
      <entry>
         <key>CSS</key>
         <value></value>
      </entry>
   </selectorCollection>
   <selectorMethod>XPATH</selectorMethod>
   <useRalativeImagePath>true</useRalativeImagePath>
   <webElementProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>tag</name>
      <type>Main</type>
      <value>html</value>
   </webElementProperties>
   <webElementProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>lang</name>
      <type>Main</type>
      <value>ja</value>
   </webElementProperties>
   <webElementProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>class</name>
      <type>Main</type>
      <value> js flexbox flexboxlegacy canvas canvastext webgl no-touch geolocation postmessage websqldatabase indexeddb hashchange history draganddrop websockets rgba hsla multiplebgs backgroundsize borderimage borderradius boxshadow textshadow opacity cssanimations csscolumns cssgradients cssreflections csstransforms csstransforms3d csstransitions fontface generatedcontent video audio localstorage sessionstorage webworkers no-applicationcache svg inlinesvg smil svgclippaths</value>
   </webElementProperties>
   <webElementProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>text</name>
      <type>Main</type>
      <value>

        
        ホーム - Online Service Gate
        
        
        
        
        
        

        

        
    #katalon{font-family:monospace;font-size:13px;background-color:rgba(0,0,0,.7);position:fixed;top:0;left:0;right:0;display:block;z-index:999999999;line-height: normal} #katalon div{padding:0;margin:0;color:#fff;} #katalon kbd{display:inline-block;padding:3px 5px;font:13px Consolas,&quot;Liberation Mono&quot;,Menlo,Courier,monospace;line-height:10px;color:#555;vertical-align:middle;background-color:#fcfcfc;border:1px solid #ccc;border-bottom-color:#bbb;border-radius:3px;box-shadow:inset 0 -1px 0 #bbb;font-weight: bold} div#katalon-spy_elementInfoDiv {color: lightblue; padding: 0px 5px 5px} div#katalon-spy_instructionDiv {padding: 5px 5px 2.5px}
    
        
            
                
                
            
        
        
            
    

        

            
                
                    
                        
                            
                        
                        
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
            

        
        
    

            
                


            
        
        
            
                Copyright © SB Technology Corp. All rights reserved.
            
        

        
    

/html[@class=&quot;js flexbox flexboxlegacy canvas canvastext webgl no-touch geolocation postmessage websqldatabase indexeddb hashchange history draganddrop websockets rgba hsla multiplebgs backgroundsize borderimage borderradius boxshadow textshadow opacity cssanimations csscolumns cssgradients cssreflections csstransforms csstransforms3d csstransitions fontface generatedcontent video audio localstorage sessionstorage webworkers no-applicationcache svg inlinesvg smil svgclippaths&quot;]</value>
   </webElementProperties>
   <webElementProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>xpath</name>
      <type>Main</type>
      <value>/html[@class=&quot;js flexbox flexboxlegacy canvas canvastext webgl no-touch geolocation postmessage websqldatabase indexeddb hashchange history draganddrop websockets rgba hsla multiplebgs backgroundsize borderimage borderradius boxshadow textshadow opacity cssanimations csscolumns cssgradients cssreflections csstransforms csstransforms3d csstransitions fontface generatedcontent video audio localstorage sessionstorage webworkers no-applicationcache svg inlinesvg smil svgclippaths&quot;]</value>
   </webElementProperties>
   <webElementXpaths>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>xpath:neighbor</name>
      <type>Main</type>
      <value>//*/text()[normalize-space(.)='']/parent::*</value>
   </webElementXpaths>
   <webElementXpaths>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>xpath:position</name>
      <type>Main</type>
      <value>//html</value>
   </webElementXpaths>
</WebElementEntity>
