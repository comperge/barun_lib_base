package org.bmcoop.test.ui;

import org.bmcoop.lib.base.view.PFragAct;
import org.bmcoop.test.ui.window8like.Window8likeAct;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UiTestMainAct extends PFragAct {

	private Button button1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(UiTestMainAct.this, Window8likeAct.class));
			}
		});
	}

	@Override
	public void abFragCreate() {
	}

	@Override
	public void abFragResume(int requestCode) {
	}

	@Override
	public Context abReturnThis() {
		return null;
	}
}