package testing.august.com.haxx.HelpClasses;

import testing.august.com.haxx.R;
import testing.august.com.haxx.pojo.TimeSeries;

/**
 * Created by Benny on 2015-03-23.
 */
public class GroupIconHelper{

        public static int setWeatherIcon(TimeSeries ts){

            String clouds = ts.getTotalAmountOfCloud();
            String weatherForm = ts.getPrecipitationForm();
            String thunder = ts.getProbabilityForThunder();
            int cloudint = Integer.valueOf(clouds);
            int thunderint = Integer.valueOf(thunder);

            //sun
            if(clouds.equals("0") && weatherForm.equals("0") && thunderint <50){
                return R.drawable.sun;
            }else if(cloudint > 0 && cloudint <5 && weatherForm.equals("0")&& thunderint <50){
                return R.drawable.suncloudm;
            }else if(cloudint > 4 && cloudint <=7 && weatherForm.equals("0")&& thunderint <50) {
                return R.drawable.suncloudh;
            }else if(weatherForm.equals("3") && clouds.equals("0")&& thunderint <50){
                return R.drawable.cloudrain;
            }else if(weatherForm.equals("1") && clouds.equals("0")&& thunderint <50){
                return R.drawable.cloudsnow;


                //cloud
            }else if (weatherForm.equals("0") && cloudint > 4 && thunderint <50){
                return R.drawable.cloud;
            }else if (weatherForm.equals("3")&& cloudint > 4  && thunderint <50){
                return R.drawable.cloudrain;
            }else if (weatherForm.equals("2") && cloudint > 4 && thunderint <50){
                return R.drawable.cloudrainsnow;
            }else if(weatherForm.equals("1") && cloudint > 4 && thunderint <50) {
                return R.drawable.cloudsnow;
            }else if(weatherForm.equals("5")||weatherForm.equals("6") && cloudint > 4 && thunderint <50) {
                return R.drawable.cloudfreezingdrizzle;
            }else if(weatherForm.equals("4") && cloudint > 4 && thunderint <50) {
                return R.drawable.clouddrizzle;


                //sun & cloud & snow/rain/drizzle
            }else if(weatherForm.equals("2") && cloudint > 0 && cloudint <5 && thunderint <50){
                return R.drawable.suncloudrainsnow;
            }else if(weatherForm.equals("3") && cloudint > 0 && cloudint <5 && thunderint <50){
                return R.drawable.suncloudrain;
            }else if(weatherForm.equals("1") && cloudint > 0 && cloudint <5 && thunderint <50 ) {
                return R.drawable.suncloudsnow;
            }else if(weatherForm.equals("5")||weatherForm.equals("6") && cloudint > 0 && cloudint <5 && thunderint <50) {
                return R.drawable.suncloudfreezingdrizzle;
            }else if(weatherForm.equals("4") && cloudint > 0 && cloudint <5 && thunderint <50){
                return R.drawable.sunclouddrizzle;


                // sun and thunder
            }else if(weatherForm.equals("2") && cloudint > 0 && cloudint <5 && thunderint >50){
                return R.drawable.suncloudthunderrainsnow;
            }else if(weatherForm.equals("3") && cloudint > 0 && cloudint <5 && thunderint >50){
                return R.drawable.suncloudthunderrain;
            }else if(weatherForm.equals("1") && cloudint > 0 && cloudint <5 && thunderint >50 ) {
                return R.drawable.suncloudthundersnow;
            }else if(weatherForm.equals("4")&& cloudint > 4 && thunderint >50){
                    return R.drawable.suncloudthunderdrizzle;

                //cloud and thunder

            }else if (weatherForm.equals("3")&& cloudint > 4  && thunderint >50){
                return R.drawable.cloudthunderrain;
            }else if (weatherForm.equals("2") && cloudint > 4 && thunderint >50){
                return R.drawable.cloudthunderrainsnow;
            }else if(weatherForm.equals("1") && cloudint > 4 && thunderint >50) {
                return R.drawable.cloudthundersnow;
            }else if(weatherForm.equals("5")||weatherForm.equals("6")&& cloudint > 4 && thunderint >50) {
                return R.drawable.cloudthunderfreezingdrizzle;
            }else if(weatherForm.equals("4")&& cloudint > 4 && thunderint >50){
                return R.drawable.cloudthunderdrizzle;
            }

            //weird cases with thunder
            else if(weatherForm.equals("0") &&  thunderint >50 && clouds.equals("0")){
                return R.drawable.cloudthunderdrizzle;
            }else if(weatherForm.equals("1") &&  thunderint >50 && clouds.equals("0")){
                return R.drawable.cloudthunderdrizzle;
            }else if(weatherForm.equals("2") &&  thunderint >50 && clouds.equals("0")){
                return R.drawable.cloudthunderdrizzle;
            }else if(weatherForm.equals("3") &&  thunderint >50 && clouds.equals("0")){
                return R.drawable.cloudthunderdrizzle;
            }else if(weatherForm.equals("4") &&  thunderint >50 && clouds.equals("0")){
                return R.drawable.cloudthunderdrizzle;
            }else if(weatherForm.equals("5") &&  thunderint >50 && clouds.equals("0")){
                return R.drawable.cloudthunderdrizzle;
            }else if(weatherForm.equals("6") &&  thunderint >50 && clouds.equals("0")){
                return R.drawable.cloudthunderdrizzle;
            }
            else{
                return R.drawable.ic_launcher;
            }
        }
}
