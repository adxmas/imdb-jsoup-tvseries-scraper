import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        final Document document = Jsoup.connect("https://www.imdb.com/title/tt0411008/episodes?season=1&ref_=tt_eps_sn_1").get();
        //https://www.imdb.com/title/tt4574334/episodes?season=1
        //https://www.imdb.com/title/tt8550800/episodes?season=1&ref_=tt_eps_sn_1

        ArrayList<String> evenAirDatesList = new ArrayList<String>();
        ArrayList<String> oddAirDatesList = new ArrayList<String>();
        ArrayList<String> evenTitlesList = new ArrayList<String>();
        ArrayList<String> oddTitleList = new ArrayList<String>();
        ArrayList<String> finalParsedMovieList = new ArrayList<String>();


        // Takes even filmu airdate
        for (Element data : document.select("div.list.detail.eplist>div.list_item.even")) {

            String airDate = data.select(".airdate").text();
            evenAirDatesList.add(airDate);
        }

        for (Element data : document.select("div.list_item.even div.info a[title]")) {

            final String title = data.attr("title");
            evenTitlesList.add(title);

        }

        // Takes odd filmu airdate
        for (Element data : document.select("div.list.detail.eplist>div.list_item.odd")) {

            String airDate = data.select(".airdate").text();
            oddAirDatesList.add(airDate);
        }

        for (Element data : document.select("div.list_item.odd div.info a[title]")) {

            final String title = data.attr("title");
            oddTitleList.add(title);

        }

        // total episodes in a season is an EQUAL number
        if ((evenTitlesList.size() + oddTitleList.size()) % 2 == 0) {
            for (int i = 0; i < evenTitlesList.size(); i++) {
                finalParsedMovieList.add(oddTitleList.get(i) + " -> " + oddAirDatesList.get(i));
                finalParsedMovieList.add(evenTitlesList.get(i) + " -> " + evenAirDatesList.get(i));
            }
        }

        if ((evenTitlesList.size() + oddTitleList.size()) % 2 != 0) {
            for (int i = 0; i < evenTitlesList.size(); i++)
            {
                finalParsedMovieList.add(oddTitleList.get(i) + " -> " + oddAirDatesList.get(i));
                finalParsedMovieList.add(evenTitlesList.get(i) + " -> " + evenAirDatesList.get(i));
            }
            finalParsedMovieList.add(oddTitleList.get(oddAirDatesList.size()-1) + " -> " + oddAirDatesList.get(oddAirDatesList.size()-1));
        }

        for(String movie: finalParsedMovieList)
        {
            System.out.println("episode: " + movie);
        }

        //finds rating for each episode
        /*for (Element data : document.select("div.ipl-rating-widget")) {

            String rating = data.select("span.ipl-rating-star__rating").text();
            finalParsedMovieList.add(rating.substring(0, 3));
        }*/

    }

}

