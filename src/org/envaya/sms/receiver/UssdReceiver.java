package org.envaya.sms.receiver;

import org.envaya.sms.App;
import org.envaya.sms.IncomingSms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class UssdReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();

		String message = bundle.getString("com.hit.ussdlib.service.message");

		String number = bundle.getString("com.hit.ussdlib.service.number");

		Log.d("USSD", number + ":" + message);

		App app = (App) context.getApplicationContext();

		IncomingSms incoming = new IncomingSms(app, number, message);

		if (incoming.isForwardable()) {
			app.inbox.forwardMessage(incoming);
		}

	}

}
