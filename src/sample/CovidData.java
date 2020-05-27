package sample;
import javafx.application.Application;
import javafx.stage.Stage;

public class CovidData extends Application{
    private String country;
    private int totalCases;
    private int newCases;
    private int totalDeaths;
    private int newDeaths;
    private int population;
    private double mortality;
    private double attackRate;
    private int day;
    private int month;
    private int year;
    private long milliseconds;
    private String totalDate;

    public CovidData(){
        this.country="";
        this.totalCases=0;
        this.newCases=0;
        this.totalDeaths=0;
        this.newDeaths=0;
        this.population=0;
        this.mortality=0;
        this.attackRate=0;
        this.day=0;
        this.month=0;
        this.year=0;
        this.milliseconds=0L;
        this.totalDate="";
    }

    public CovidData(String country,int totalCase,int newCases,int totalDeaths,int newDeaths,int population,double mortality,double attackRate,int day,int month,int year,long milliseconds,String totalDate)
    {
        this.country = country;
        this.totalCases=totalCase;
        this.newCases=newCases;
        this.totalDeaths=totalDeaths;
        this.newDeaths=newDeaths;
        this.population=population;
        this.mortality=mortality;
        this.attackRate=attackRate;
        this.day=day;
        this.month=month;
        this.year=year;
        this.milliseconds=milliseconds;
        this.totalDate=totalDate;


    }

    // set-get


    public String getCountry() {
        return country;
    }
    public void setCountry(String country)
    {
        this.country=country;
    }
    public int getTotalCases()
    {
        return totalCases;
    }
    public void setTotalCases(int totalCases)
    {
        this.totalCases=totalCases;
    }
    public int getNewCases()
    {
        return newCases;
    }
    public void setNewCases(int newCases)
    {
        this.newCases=newCases;
    }
    public int getTotalDeaths()
    {
        return totalDeaths;
    }
    public void setTotalDeaths(int totalDeaths)
    {
        this.totalDeaths=totalDeaths;
    }
    public int getNewDeaths()
    {
        return newDeaths;
    }
    public void setNewDeaths(int newDeaths)
    {
        this.newDeaths=newDeaths;
    }
    public int getPopulation()
    {
        return population;
    }
    public void setPopulation(int population)
    {
        this.population=population;
    }


    public double getMortality()
    {
        return mortality;
    }
    public void setMortality(double mortality)
    {
        this.mortality=mortality;
    }
    public double getAttackRate()
    {
        return attackRate;
    }
    public void setAttackRate(double attackRate)
    {
        this.attackRate=attackRate;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public long getMilliseconds() {
        return milliseconds;
    }

    public void setMilliseconds(long milliseconds) {
        this.milliseconds = milliseconds;
    }

    public String getTotalDate() {
        return totalDate;
    }

    public void setTotalDate(String totalDate) {
        this.totalDate = totalDate;
    }

    @Override
    public void start(Stage stage) throws Exception {

    }

/*    @Override
    public int compareTo(CovidData other) {
        return country.compareTo(other.country);
    }*/

}
