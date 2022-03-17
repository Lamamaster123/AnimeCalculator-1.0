

public class Show {
   public String name;
   public int episodes;
   public boolean isMovie;
   public int watchNumber;
   
   public Show(String name, int episodes) {
      this(name, episodes, false, 1);
   }
   
   public Show(String name, int episodes, boolean isMovie) {
      this(name, episodes, isMovie, 1); 
   }
   
   public Show(String name, int episodes, boolean isMovie, int watchNumber) {
      this.name = name;
      this.episodes = episodes;
      this.watchNumber = watchNumber;
      this.isMovie = isMovie;
   }
   
   public String toString() {
      String showType = isMovie ? "Movie" : "Show";
      return String.format("%-55s | %-5d | %-5s | %-3d", name, episodes, showType, watchNumber);
   }
}