package dk.voidcode.ugenr;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import dk.voidcode.ugenr.R;
import dk.voidcode.ugenr.WidgetActivity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;

import android.content.Context;
import android.content.Intent;
import android.sax.StartElementListener;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

public class WidgetActivity extends AppWidgetProvider {
	public static String MAIN_ACTION;
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		final int N = appWidgetIds.length;

        // Perform this loop procedure for each App Widget that belongs to this provider
        for (int i=0; i<N; i++) {
            int appWidgetId = appWidgetIds[i];

            // Create an Intent to launch event calendar
            Calendar cal = Calendar.getInstance();              
            Intent calendar_intent = new Intent(Intent.ACTION_EDIT);
            calendar_intent.setType("vnd.android.cursor.item/event");
            calendar_intent.putExtra("beginTime", cal.getTimeInMillis());
            calendar_intent.putExtra("allDay", false);
            calendar_intent.putExtra("rrule", "FREQ=DAILY");
            calendar_intent.putExtra("endTime", cal.getTimeInMillis()+60*60*1000);
            
            PendingIntent calendar_pendingIntent = PendingIntent.getActivity(context, 0, calendar_intent, 0);
            
            // Get the layout for the App Widget and attach an on-click listener
            // to the button
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
            views.setOnClickPendingIntent(R.id.widget, calendar_pendingIntent);          
            
            // Get Week number and show it in widget textview
    	    Calendar calendar = new GregorianCalendar();
    	    calendar.setTime(new Date());
    	    String s_weekofyear = Integer.toString(calendar.get(Calendar.WEEK_OF_YEAR));
            views.setTextViewText(R.id.widget_textView, context.getString(R.string.youAreInWeek)+" "+s_weekofyear);
            
            //textView_widget
            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
        
    }
	public void openWeekCalender(View v)
    {
		
    }
}
