package translationVisualization;

import org.annolab.tt4j.*;

public class Example {
	public static void main(String[] args) throws Exception{
		System.setProperty("treetagger.home", "src\\org\\annolab\\tt4j\\TreeTagger");
		TreeTaggerWrapper<String> tt = new TreeTaggerWrapper<String>();
		try {
			tt.setModel("src\\org\\annolab\\tt4j\\german.par");
			tt.setHandler(new TokenHandler<String>()
			{
				public void token(String token, String pos, String lemma)
				{
					System.out.println(token + "\t" + pos + "\t" + lemma);
				}
			});
			tt.process(new String[] { "This", "is", "a", "test", "." });
		}
		finally {
			tt.destroy();
		}
	}

}
