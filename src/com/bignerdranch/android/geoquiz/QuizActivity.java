package com.bignerdranch.android.geoquiz;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends Activity {

	private static final String TAG = "QuizActivity";
	private static final String KEY_INDEX = "index";
	
	private Button mTrueButton;
	private Button mFalseButton;
	private ImageButton mNextButton;
	private ImageButton mPrevButton;
	private TextView mQuestionTextView;

	private TrueFalse[] mQuestionBank = new TrueFalse [] {
		new TrueFalse( R.string.question_africa, false ),
		new TrueFalse(R.string.question_americas, true),
		new TrueFalse(R.string.question_asia, true),
		new TrueFalse(R.string.questions_ocean, true),
		new TrueFalse(R.string.question_mideast, false)
	};
	
	private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
		Log.d(TAG, "onCreate() called");

        setContentView(R.layout.activity_quiz);
        
        if ( savedInstanceState != null )
        {
        	mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }
        
        mTrueButton = (Button)findViewById(R.id.true_button);
        mFalseButton = (Button)findViewById(R.id.false_button);
        mNextButton = (ImageButton)findViewById(R.id.next_button);
        mPrevButton = (ImageButton)findViewById(R.id.prev_button);
        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
        showQuestion();
        
        /* Button click listeners */
        mTrueButton.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				checkAnswer( true );
			}
		}); 
        
        mFalseButton.setOnClickListener( new View.OnClickListener( ) {
			
			@Override
			public void onClick(View v) {
				checkAnswer( false );
			}
		});
        
        mNextButton.setOnClickListener( new View.OnClickListener( ) {
			
			@Override
			public void onClick(View v) {
				updateQuestionNext();			
			}
		});
        
        mPrevButton.setOnClickListener( new View.OnClickListener( ) {
			
			@Override
			public void onClick(View v) {
				updateQuestionPrev();			
			}
		});
        
    }

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		Log.i( TAG, "onSaveInstanceState() called");
		
		outState.putInt(KEY_INDEX, mCurrentIndex);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d(TAG, "onDestroy() called");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.d(TAG, "onPause() called");		
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.d(TAG, "onResume() called");				
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.d(TAG, "onStop() called");				
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.d(TAG, "onStart() called");						
	}
	
   
    private void updateQuestionNext()
    {
    	mCurrentIndex =  (mCurrentIndex + 1) % mQuestionBank.length;
    	showQuestion();
    }

    private void updateQuestionPrev()
    {
    	mCurrentIndex =  (mCurrentIndex == 0) ? mQuestionBank.length - 1: mCurrentIndex - 1;
    	showQuestion();
    }
    
    private void showQuestion()
    {
    	try {
    		mQuestionTextView.setText(mQuestionBank[mCurrentIndex].getQuestion());  
    	} catch (ArrayIndexOutOfBoundsException e) {
    		Log.e(TAG, "mCurrentIndex was out of bounds", e);
    	}
    }

    private void checkAnswer( boolean answer )
    {
    	int messageResId = 0;
    	
    	if ( mQuestionBank[mCurrentIndex].isTrueQuestion() == answer )
    	{
    		messageResId = R.string.correct_toast;
    	}
    	else
    	{
    		messageResId = R.string.incorrect_toast;
    	}
    	
    	Toast.makeText(QuizActivity.this, messageResId, Toast.LENGTH_SHORT).show();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.quiz, menu);
        return true;
    }

    
}
