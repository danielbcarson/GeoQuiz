package com.bignerdranch.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends Activity {
	
	private boolean mAnswerIsTrue;
	private TextView mAnswerTextView;
	private Button mShowAnswerButton;
	private boolean mAnswerShown = false;
	private TextView mAPILevelTextView;
	
	public static final String EXTRA_ANSWER_IS_TRUE =
			"com.bignerdranch.geoquiz.answer_is_true";
	public static final String EXTRA_ANSWER_SHOWN =
			"com.bignerdranch.geoquiz.answer_shown";
	private static final String KEY_ANSWER_SHOWN = "answer_shown";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_cheat);
		
		/* get the answer value passed by the parent activity */
		mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
		
		/** 
		 * get the saved value of whether the user has been shown the
		 * correct answer (i.e., cheated)
		 */
		if ( savedInstanceState != null )
		{
			mAnswerShown = savedInstanceState.getBoolean(KEY_ANSWER_SHOWN);
		}
		setAnswerShown(mAnswerShown);
		
		mAnswerTextView = (TextView)findViewById(R.id.answerTextView);
		
		/* if the answer was already shown display the answer */
		if ( mAnswerShown ) {
			if (mAnswerIsTrue ) {
				mAnswerTextView.setText(R.string.true_button);
			}
			else {
				mAnswerTextView.setText(R.string.false_button);
			}			
		}
		
		mShowAnswerButton = (Button)findViewById(R.id.showAnswerButton);		
		mShowAnswerButton.setOnClickListener( new View.OnClickListener( ) {
			
			@Override
			public void onClick(View v) {
				
				if (mAnswerIsTrue ) {
					mAnswerTextView.setText(R.string.true_button);
				}
				else {
					mAnswerTextView.setText(R.string.false_button);
				}
				mAnswerShown = true;
				setAnswerShown(mAnswerShown);
			}
		});
		
		// show Android API Level of device
		mAPILevelTextView = (TextView)findViewById(R.id.apiLevel);
		int apiLevel = Build.VERSION.SDK_INT;
		mAPILevelTextView.setText("API Level " + apiLevel );
	}

	/**
	 * Set the return result for the parent activity
	 * 
	 * @param IsAnswerShown
	 */
	private void setAnswerShown( boolean IsAnswerShown)
	{
		Intent data = new Intent();
		data.putExtra(EXTRA_ANSWER_SHOWN, IsAnswerShown);
		/* set the return result sent back to the parent activity */
		setResult(RESULT_OK, data);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState ) {
		super.onRestoreInstanceState(savedInstanceState);
		if ( savedInstanceState != null )
		{
			mAnswerShown = savedInstanceState.getBoolean(KEY_ANSWER_SHOWN);
			setAnswerShown(mAnswerShown);
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putBoolean(KEY_ANSWER_SHOWN, mAnswerShown);
	}
	
}
