
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadCSV {// start class
	public static void main(String[] args) {// start main

		String csvFile = "tmdb_5000_movies.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		int counter = 0;
		String[] country = new String[50000];

		try {
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				country[counter] = line;
				counter++;
			}
			parser(country);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("Done");
	}// end main

	public static void parser(String[] country) {
		int count = 0;
		int subString = 1;
		String temp = "";
		String patternGenre = ("\\d+");
		int bracketHolder = 0;
		int tempHold = 0;
		int countGenre = 0;
		int genreCounter = 0;
		int z = 0;
		String keyword = "";

		String[] genres = new String[1000];
		String[] genresUnique = new String[1000];
		List<String> Action = new ArrayList<>();
		List<String> Adventure = new ArrayList<>();
		List<String> Fantasy = new ArrayList<>();
		List<String> ScienceFiction = new ArrayList<>();
		List<String> Crime = new ArrayList<>();
		List<String> Drama = new ArrayList<>();
		List<String> Thriller = new ArrayList<>();
		List<String> Animation = new ArrayList<>();
		List<String> Family = new ArrayList<>();
		List<String> Western = new ArrayList<>();
		List<String> Comedy = new ArrayList<>();
		List<String> Romance = new ArrayList<>();
		List<String> Horror = new ArrayList<>();
		List<String> Mystery = new ArrayList<>();
		List<String> History = new ArrayList<>();
		List<String> War = new ArrayList<>();
		List<String> Music = new ArrayList<>();
		List<String> Documentary = new ArrayList<>();
		List<String> Foreign = new ArrayList<>();
		List<String> TVMovie = new ArrayList<>();
		List<String> Keywords = new ArrayList<>();
		List<String> Title = new ArrayList<>();
		List<String> Status = new ArrayList<>();
		List<String> VoteAverage = new ArrayList<>();
		List<String> VoteCount = new ArrayList<>();

		while (country[count] != null) {// start while
			String IDout = "";
			String genreFinal = "";
			String idSubstring = "";
			int idStart = 0;
			String keySubstring = "";
			String keySubstring2 = "";
			int subString2 = 0;
			String keyWordsFinal = "";
			int counterKeywords = 0;

			Pattern pattern = Pattern.compile("\\d+");
			Matcher matcher = pattern.matcher(country[count]);

			idStart = country[count].indexOf("]");
			if (idStart > 0) {
				idSubstring = country[count].substring(idStart);
				Matcher m2 = Pattern.compile("\\d+").matcher(idSubstring);
				if (m2.find()) {
					IDout = m2.group(0);
					// System.out.println("ID: " + IDout);// ID
				}
			}
			Matcher m = Pattern.compile("\\[([^]]+)\\]").matcher(country[count]);
			if (m.find()) {// start find

				String[] genreInput = m.group(1).split(",");
				for (int b = 0; b < genreInput.length; b++) {
					if ((b + 1) < genreInput.length) {// start if

						genres[b] = new StringBuilder().append(genreInput[b].replaceAll("[^0-9]", "")).append(" ")
								.append(genreInput[b + 1].replaceAll("[^a-zA-Z]", "").substring(4)).toString();

						String tempString = "";
						tempString = genres[b].replaceAll("\\d", "");
						tempString = tempString.replaceAll("\\s", "");
						tempString = tempString.replaceAll(",", "");

						if (genres[b].contains("Action")) {
							genreFinal = IDout;
							Action.add(genreFinal);
							genreFinal = "";
						}
						if (genres[b].contains("Adventure")) {
							genreFinal = IDout;
							Adventure.add(genreFinal);
							genreFinal = "";
						}
						if (genres[b].contains("Fantasy")) {
							genreFinal = IDout;
							Fantasy.add(genreFinal);
							genreFinal = "";
						}
						if (genres[b].contains("ScienceFiction")) {
							genreFinal = IDout;
							ScienceFiction.add(genreFinal);
							genreFinal = "";
						}
						if (genres[b].contains("Crime")) {
							genreFinal = IDout;
							Crime.add(genreFinal);
							genreFinal = "";
						}
						if (genres[b].contains("Drama")) {
							genreFinal = IDout;
							Drama.add(genreFinal);
							genreFinal = "";
						}
						if (genres[b].contains("Thriller")) {
							genreFinal = IDout;
							Thriller.add(genreFinal);
							genreFinal = "";
						}
						if (genres[b].contains("Animation")) {
							genreFinal = IDout;
							Animation.add(genreFinal);
							genreFinal = "";
						}
						if (genres[b].contains("Family")) {
							genreFinal = IDout;
							Family.add(genreFinal);
							genreFinal = "";
						}
						if (genres[b].contains("Western")) {
							genreFinal = IDout;
							Western.add(genreFinal);
							genreFinal = "";
						}
						if (genres[b].contains("Comedy")) {
							genreFinal = IDout;
							Comedy.add(genreFinal);
							genreFinal = "";
						}
						if (genres[b].contains("Romance")) {
							genreFinal = IDout;
							Romance.add(genreFinal);
							genreFinal = "";
						}
						if (genres[b].contains("Horror")) {
							genreFinal = IDout;
							Horror.add(genreFinal);
							genreFinal = "";
						}
						if (genres[b].contains("Mystery")) {
							genreFinal = IDout;
							Mystery.add(genreFinal);
							genreFinal = "";
						}
						if (genres[b].contains("History")) {
							genreFinal = IDout;
							History.add(genreFinal);
							genreFinal = "";
						}
						if (genres[b].contains("War")) {
							genreFinal = IDout;
							War.add(genreFinal);
							genreFinal = "";
						}
						if (genres[b].contains("Music")) {
							genreFinal = IDout;
							Music.add(genreFinal);
							genreFinal = "";
						}
						if (genres[b].contains("Documentary")) {
							genreFinal = IDout;
							Documentary.add(genreFinal);
							genreFinal = "";
						}
						if (genres[b].contains("Foreign")) {
							genreFinal = IDout;
							Foreign.add(genreFinal);
							genreFinal = "";
						}
						if (genres[b].contains("TVMovie")) {
							genreFinal = IDout;
							TVMovie.add(genreFinal);
							genreFinal = "";
						}

						b++;
					} // end if
				} // end for
			} // end find

			subString = country[count].indexOf("]");

			if (subString > 1) {
				keySubstring = country[count].substring(subString);

				subString2 = keySubstring.indexOf("[") - 1;
				keySubstring2 = keySubstring.substring(subString2);
				String firstFour = keySubstring2.substring(0, 4);
				if (firstFour.contains(",[],")) {
					keySubstring2 = "";
				}

				Matcher m4 = Pattern.compile("\\[([^]]+)\\]").matcher(keySubstring2);
				if (m4.find()) {
					String[] keywordInput = m4.group(1).split(",");
					keyword = IDout + ",";
					for (int x = 0; x < keywordInput.length; x++) {
						boolean condition = keywordInput[x] != null && (x + 1) < keywordInput.length
								&& keywordInput[x + 1] != null && keywordInput[x + 1].contains("name")
								&& !keywordInput[x + 1].equals(keywordInput[x]) && !keywordInput[x + 1].equals(null)
								&& counterKeywords <= 5;
						// end if
						if (condition) {
							keyword += keywordInput[x + 1].replaceAll("[^a-zA-Z]", "").substring(4) + ",";
							counterKeywords++;
						}
					} // end for
					String keywordFinal = keyword.substring(0, keyword.lastIndexOf(","));
					Keywords.add(keywordFinal);
				}

			}

			if (bracketHolder > 1) {
				Matcher m5 = Pattern.compile("\\[([^]]+)\\]").matcher(country[count].substring(bracketHolder));
				if (m5.find()) {
					System.out.println("Production Companies: " + m5.group(1));// Production Companies
					tempHold = m5.group(0).length() + 1;
					subString = country[count].substring(bracketHolder + 1).indexOf("[");
					bracketHolder = bracketHolder + tempHold + subString;
				}
			}
			if ((bracketHolder + tempHold) > 0) {
				Matcher m6 = Pattern.compile("\\[([^]]+)\\]").matcher(country[count].substring(bracketHolder));
				if (m6.find()) {

					tempHold = m6.group(0).length() + 1;
					subString = country[count].substring(bracketHolder + 1).indexOf("[");
					bracketHolder = bracketHolder + tempHold + subString;
				}
			}
			if (bracketHolder > 1) {

				Matcher m7 = Pattern.compile("\\[([^]]+)\\]").matcher(country[count].substring(bracketHolder));
				if (m7.find()) {

					tempHold = m7.group(0).length() + 1;
					subString = country[count].substring(bracketHolder + 1).indexOf("[");
					bracketHolder = bracketHolder + tempHold + subString;
				}
			}

			int test = country[count].lastIndexOf("]");
			keySubstring2 = country[count].substring(test + 3);
			int track = 0;
			String[] tokens = keySubstring2.split(",");
			String status1 = new StringBuilder().append(IDout).append(",").append(tokens[track]).toString();
			Status.add(status1);

			String tagline = tokens[track];
			track++;
			if (tagline.startsWith("\"")) {
				tagline = tagline + tokens[track];
				track++;

			}
			track++;
			if (track < tokens.length) {
				if (tokens[track].contains("\"")) {
					track++;
				}
				if (tokens[track].contains("\"")) {
					track++;
				}
				if (tokens[track].contains("Virgin")) {
					track++;
				}
				String titler = new StringBuilder().append(IDout).append(",").append(tokens[track].trim()).toString();
				Title.add(titler);
				track++;
			}

			while (track < tokens.length && !tokens[track].matches("^\\d.\\d$")) {
				track++;
			}

			if (track < tokens.length) {
				String vote_average = new StringBuilder().append(IDout).append(",").append(tokens[track]).toString();
				VoteAverage.add(vote_average);

				track++;
			}

			if (track < tokens.length) {
				String vote_count = new StringBuilder().append(IDout).append(",").append(tokens[track]).toString();

				VoteCount.add(vote_count);
			}

			count++;

		} // end while

		tester(Action, "Action");
		tester(Adventure, "Adventure");
		tester(Fantasy, "Fantasy");
		tester(ScienceFiction, "ScienceFiction");
		tester(Crime, "Crime");
		tester(Drama, "Drama");
		tester(Thriller, "Thriller");
		tester(Animation, "Animation");
		tester(Family, "Family");
		tester(Western, "Western");
		tester(Comedy, "Comedy");
		tester(Romance, "Romance");
		tester(Horror, "Horror");
		tester(Mystery, "Mystery");
		tester(History, "History");
		tester(War, "War");
		tester(Music, "Music");
		tester(Documentary, "Documentary");
		tester(Foreign, "Foreign");
		tester(TVMovie, "TVMovie");
		tester(Keywords, "Keywords");
		tester(Title, "Title");
		tester(Status, "Status");
		tester(VoteCount, "VoteCount");
		tester(VoteAverage, "VoteAverage");

	}

	public static void tester(List<String> array, String name) {

		File logFile = new File(name);

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(logFile))) {
			System.out.println("File was written to: " + logFile.getCanonicalPath());

			for (String m : array) {
				bw.write(m + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean isInAry(String current, String[] tempAry) {// isinarystart
		boolean value = false;
		if (current.matches(".*[0-9].*")) {
			for (String aTempAry : tempAry) {
				if (aTempAry != null && aTempAry.equals(current)) {
					value = true;
				} // end if
			} // end for
		} else {
			for (String aTempAry : tempAry) {

				if (aTempAry != null && aTempAry.contains(current)) {
					value = true;
				}
			}
		}
		return value;
	} // isinary end

}// end class