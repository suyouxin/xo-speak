package com.morphoss.xo_speak.listeners;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class OnMouthStyleSelectedListener implements OnItemSelectedListener {

	@Override
	public void onItemSelected(AdapterView parent, View view, int pos, long id) {

		/*Toast.makeText(
				parent.getContext(),
				"Mouth style selected : "
						+ parent.getItemAtPosition(pos).toString(),
				Toast.LENGTH_SHORT).show();*/
	}

	@Override
	public void onNothingSelected(AdapterView parent) {

	}
}
