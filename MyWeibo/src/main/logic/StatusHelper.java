package main.logic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;

/**
 * 
 * @author Zheng Chen
 *
 */
public class StatusHelper {

	private SpannableStringBuilder replaced;

	/**
	 * 
	 * @return ��������������status������ʾ
	 */
	public SpannableStringBuilder getReplaced() {
		return replaced;
	}

	public StatusHelper(Context context, String origintext) {
		origintext = origintext + " ";
		SpannableStringBuilder spannable = new SpannableStringBuilder(
				origintext);
		//���ø���
		CharacterStyle span1 = null;
//		Pattern p = Pattern.compile("@.*?[ :()����]");
		//����ƥ��"�ᵽ"(@)��"����"(#...#)��������ʽ
		Pattern p1 = Pattern.compile("@.*?(?=\\W)|#.*?#");
		Matcher m1 = p1.matcher(origintext);
		while (m1.find()) {
			span1 = new ForegroundColorSpan(Color.BLUE);
			spannable.setSpan(span1, m1.start(), m1.end(),
					Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
		}
		spannable = (SpannableStringBuilder) spannable.subSequence(0,
				spannable.length() - 1);
		//�滻��������Ϊ����ͼƬ
//		Drawable drawable = context.getResources().getDrawable(R.drawable.smile);
//		ImageSpan span2 = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
//		ImageSpan span2 = new ImageSpan(context, R.drawable.smile);
//		Pattern p2 = Pattern.compile("\\[�Ǻ�\\]");
//		Matcher m2 = p2.matcher(spannable);
//		while(m2.find()){
//			spannable.setSpan(span2, m2.start(), m2.end(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
//		}
		
		replaced = spannable;
	}
}
