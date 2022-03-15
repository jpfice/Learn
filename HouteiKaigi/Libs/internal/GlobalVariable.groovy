package internal

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.main.TestCaseMain


/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */
public class GlobalVariable {
     
    /**
     * <p>Profile default : &#27861;&#23450;&#20250;&#35696;&#12471;&#12473;&#12486;&#12512;  &#12486;&#12473;&#12488;&#29992;</p>
     */
    public static Object G_BaseUrl
     
    /**
     * <p>Profile default : &#20107;&#21209;&#23616;&#12486;&#12473;&#12488;&#65296;&#65297;</p>
     */
    public static Object G_User_J01
     
    /**
     * <p>Profile default : &#20107;&#21209;&#23616;&#12486;&#12473;&#12488;&#65296;&#65298;</p>
     */
    public static Object G_User_J02
     
    /**
     * <p>Profile default : &#35696;&#38988;&#30331;&#37682;&#32773;&#12486;&#12473;&#12488;&#65296;&#65297;</p>
     */
    public static Object G_User_G01
     
    /**
     * <p>Profile default : &#35696;&#38988;&#30331;&#37682;&#32773;&#12486;&#12473;&#12488;&#65296;&#65298;</p>
     */
    public static Object G_User_G02
     
    /**
     * <p>Profile default : &#12497;&#12473;&#12527;&#12540;&#12489;(Qqmx20220101)</p>
     */
    public static Object G_PassWord
     
    /**
     * <p></p>
     */
    public static Object G_JobCount
     
    /**
     * <p></p>
     */
    public static Object G_FilePath
     
    /**
     * <p></p>
     */
    public static Object sData
     
    /**
     * <p>Profile default : &#20250;&#35696;&#21517;</p>
     */
    public static Object G_MeetName
     
    /**
     * <p>Profile default : &#20250;&#35696;&#31278;&#39006;</p>
     */
    public static Object G_MeetType
     
    /**
     * <p>Profile default : &#26178;&#38291;&#12464;&#12523;&#12540;&#12503;&#30058;&#21495;</p>
     */
    public static Object G_TimeNum
     
    /**
     * <p></p>
     */
    public static Object G_RowNum
     
    /**
     * <p></p>
     */
    public static Object G_FileName
     

    static {
        try {
            def selectedVariables = TestCaseMain.getGlobalVariables("default")
			selectedVariables += TestCaseMain.getGlobalVariables(RunConfiguration.getExecutionProfile())
            selectedVariables += TestCaseMain.getParsedValues(RunConfiguration.getOverridingParameters())
    
            G_BaseUrl = selectedVariables['G_BaseUrl']
            G_User_J01 = selectedVariables['G_User_J01']
            G_User_J02 = selectedVariables['G_User_J02']
            G_User_G01 = selectedVariables['G_User_G01']
            G_User_G02 = selectedVariables['G_User_G02']
            G_PassWord = selectedVariables['G_PassWord']
            G_JobCount = selectedVariables['G_JobCount']
            G_FilePath = selectedVariables['G_FilePath']
            sData = selectedVariables['sData']
            G_MeetName = selectedVariables['G_MeetName']
            G_MeetType = selectedVariables['G_MeetType']
            G_TimeNum = selectedVariables['G_TimeNum']
            G_RowNum = selectedVariables['G_RowNum']
            G_FileName = selectedVariables['G_FileName']
            
        } catch (Exception e) {
            TestCaseMain.logGlobalVariableError(e)
        }
    }
}
