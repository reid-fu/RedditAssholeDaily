public class CrawlerRun {

    public static void main(String[] args) {
        FrontPageCrawler fpc = new FrontPageCrawler();
        fpc.initiateCrawling();
        Set<String> users = UserNameFetcher.getUserSet();
        Map<String, Double> map = new TreeMap<>();
        
        for(String str : users){
            RedditParser rp = new RedditParser(str);
            CommentJudge judge = new AlchemyJudge();
            while((comment = parser.nextComment()) != null){
                judge.score(comment);
            }
            map.put(str, judge.cumulativeScore());
        }
        
        System.out.println(map);
    }

}
