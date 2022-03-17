import java.util.*;
import java.io.*;

public class Calculator {
   private List<Show> shows;
   private final double MINS_PER_SHOW = 22.5;

   public Calculator(File file) throws FileNotFoundException {
      this.shows = initializeShows(file);
   }
   
   private List<Show> initializeShows(File file) throws FileNotFoundException {
      Scanner scanner = new Scanner(file);
      List<Show> answer = new ArrayList<>();
      
      while (scanner.hasNextLine()) {
         String[] line = scanner.nextLine().split("\\|");
         
         String name = line[0];
         int eps = Integer.parseInt(line[1]);
         boolean isMovie = line[2].equals("Movie");
         int watchNumber = Integer.parseInt(line[3]);
         
         answer.add(new Show(name, eps, isMovie, watchNumber));
         
      }
      
      return answer;
   }
   
   public void edit(Scanner input) {
      try {
         System.out.print("What is the number of the show? ");
         int number = Integer.parseInt(input.nextLine());
         Show show = shows.get(number - 1);
         
         System.out.println("----------------------------");
         System.out.println("Name: " + show.name);
         System.out.println("Episodes: " + show.episodes);
         System.out.println("Movie: " + show.isMovie);
         System.out.println("Rewatch: " + show.watchNumber);
         System.out.println("----------------------------");

         System.out.print("What do you want to edit? (name, episodes, movie, or rewatch) ");
         String answer = input.nextLine();
         
         if (answer.equalsIgnoreCase("name")) {
            System.out.print("What do you want to change it to? ");
            
            show.name = input.nextLine();
         } else if (answer.equalsIgnoreCase("episodes")) {
            System.out.print("What do you want to change it to? ");
            
            show.episodes = Integer.parseInt(input.nextLine());
         } else if (answer.equalsIgnoreCase("movie")) {
            show.isMovie = !show.isMovie;
         } else if (answer.equalsIgnoreCase("rewatch")) {
            System.out.print("What do you want to change it to? ");
            
            show.watchNumber = Integer.parseInt(input.nextLine());
         } else {
            System.out.println("Invalid format");
         }
         
      } catch (Exception e) {
         System.out.println("Invalid input");
      }
   }
   
   public void add(Scanner input) {
      try {
         System.out.print("What is the name of the anime? ");
         String name = input.nextLine();
         
         System.out.print("How many episodes (each episode is ~20 min)? ");
         int episodes = Integer.parseInt(input.nextLine());
         
         System.out.print("Is it a movie (y/n)? ");
         boolean isMovie = input.nextLine().equalsIgnoreCase("y");
         
         System.out.print("How many times did you watch it? ");
         int watchNumber = Integer.parseInt(input.nextLine());
      
         shows.add(new Show(name, episodes, isMovie, watchNumber));
      } catch (Exception e) {
         System.out.println("Invalid format");
      }
   }
   
   public void delete(Scanner input) {
      System.out.print("What is the name of the anime? ");
      String name = input.nextLine();
      
      int index = -1;
      for (int i = 0; i < shows.size(); i++) {
         if (shows.get(i).name.trim().equalsIgnoreCase(name.trim())) {
            index = i;
         }
      }
      
      if (index != -1) {
         shows.remove(index);
      } else {
         System.out.println("I couldn't find that show!");
      }
   }
   
   public void analyze() {
      int episodes = 0;
      int totalAnime = shows.size();
      int movies = 0;
      
      for (Show show : shows) {
         episodes += show.episodes;
         if (show.isMovie) {
            movies++;
         }
      }
      
      double minutes = episodes * MINS_PER_SHOW;
      double hours = minutes / 60;
      double days = hours / 24;      
      
      System.out.println();
      System.out.println("---GENERAL---");
      System.out.printf("Total anime: %d\n", totalAnime);
      System.out.printf("Shows: %d\n", totalAnime - movies);
      System.out.printf("Movies: %d\n", movies);
      System.out.println();
      
      System.out.println("---TIME---");
      System.out.printf("Minutes: %.1f \n", minutes);
      System.out.printf("Hours: %.1f \n", hours);
      System.out.printf("Days: %.1f \n", days);
   }
   
   public void print() {
      for (int i = 0; i < shows.size(); i++) {
         System.out.printf("%-5s%s\n", (i + 1) + ".", shows.get(i).toString());
      }
   }
   
   public void save(String filename) throws IOException {
      File file = new File(filename);
      file.delete();
      file.createNewFile();
      
      BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
      
      for (Show show : shows) {
         String showType = show.isMovie ? "Movie" : "Show";
         writer.write(show.name + "|" + show.episodes + "|" + showType + "|" + show.watchNumber);
         writer.newLine();
      }
      writer.close();
   }
   
   
}