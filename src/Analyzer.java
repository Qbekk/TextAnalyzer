import java.util.Date;
public class Analyzer {
	Text text;
	Date start,end;
	private int wordTotal,sentenceTotal,wordDominant,wordCharSum;
	public int getWordCharSum() {
		return wordCharSum;
	}


	private double wordLenAvg,wordLenStdDev,sentenceLenAvg;
	private int[] wordDistribution;
	Analyzer(Text text){
		this.text=text;
		
	}
	
	
	public void run(){
		//init values
		wordDominant=0;
		wordTotal=0;
		sentenceTotal=0;
		wordCharSum=0;
		int sentenceLenSum=0;
		int tempWordLen;
		int tempSentenceLen;
		wordDistribution=new int[100];
		System.out.print("\nStarting text analysis...");
		start=new Date();
		for(int i=0;i<wordDistribution.length-1;i++)
			wordDistribution[i]=0;
		//iterate all words
		for(Sentence sent:text.getSentences()){
			for(Word word:sent.getWords()){
				wordTotal++;
				tempWordLen=word.getContent().length();
				wordCharSum+=tempWordLen;
				if(tempWordLen<=wordDistribution.length-2)
					wordDistribution[tempWordLen-1]++;//-1
				else
					wordDistribution[wordDistribution.length-1]++;
				
			}
			tempSentenceLen=sent.getWords().size();
			sentenceLenSum+=tempSentenceLen;
			sentenceTotal++;
			
		}
		wordLenAvg=(double)wordCharSum/(double)wordTotal;
		wordLenStdDev=computeStdDev(wordLenAvg,wordDistribution);
		sentenceLenAvg=(double)sentenceLenSum/(double)sentenceTotal;
		int max=0;
		boolean hasDominant=true;
		for(int i=0;i<wordDistribution.length-1;i++){
			if(wordDistribution[i]>max){
				max=wordDistribution[i];
				wordDominant=i+1;
				hasDominant=true;
			}
			else if(wordDistribution[i]==max&&max!=0){
				hasDominant=false;
			}
		}
		if (!hasDominant){
			wordDominant=0;
		}
		
		end=new Date();
		System.out.print("Done! Elapased time:" +(end.getTime()-start.getTime())+" ms");
	}
	

	private double computeStdDev(double avg, int[] distribution){
		double result=0;
		for(int i=0;i<distribution.length-1;i++){//calculates variance //
			result+=distribution[i]*Math.pow((avg-(i+1)),2);//+1
		}
		result/=wordTotal;
		
		return Math.sqrt(result);
	}
	public int getWordDominant() {
		return wordDominant;
	}

	public Text getText() {
		return text;
	}


	public int getWordTotal() {
		return wordTotal;
	}


	public int getSentenceTotal() {
		return sentenceTotal;
	}


	public double getWordLenAvg() {
		return wordLenAvg;
	}


	public double getWordLenStdDev() {
		return wordLenStdDev;
	}


	public double getSentenceLenAvg() {
		return sentenceLenAvg;
	}


	public int[] getWordDistribution() {
		return wordDistribution;
	}


	
}