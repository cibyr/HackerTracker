package com.shortstack.hackertracker.Utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.shortstack.hackertracker.Activity.HomeActivity;
import com.shortstack.hackertracker.Application.HackerTrackerApplication;
import com.shortstack.hackertracker.Fragment.DetailsFragment;
import com.shortstack.hackertracker.Model.Default;
import com.shortstack.hackertracker.Model.OfficialList;
import com.shortstack.hackertracker.R;
import com.shortstack.hackertracker.Schedule.SchedulePagerFragment;

import java.io.File;

/**
 * Created by whitneychampion on 7/11/14.
 */
public class DialogUtil {

    public static AlertDialog darknetDialog(Context context, String message) {

        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_darknet, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        AlertDialog dialog = builder.create();

        dialog.setView(view, 0, 0, 0, 0);
        dialog.setCanceledOnTouchOutside(false);

        TextView dialogText = (TextView) view.findViewById(R.id.dialog_text);
        dialogText.setText(message);

        Button cancelButton = (Button) view.findViewById(R.id.dismiss);
        final Dialog finalDialog = dialog;
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalDialog.dismiss();
            }
        });

        return dialog;

    }

    public static ProgressDialog getProgressDialog(Context context, String message) {

        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage(message);
        return progressDialog;

    }

    public static void hideSpinner(ProgressDialog progressDialog) {
        if (progressDialog.isShowing()) {
            progressDialog.hide();
        }
    }

    public static AlertDialog apiErrorDialog(final Context context) {

        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_error, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        AlertDialog dialog = builder.create();

        dialog.setView(view, 0, 0, 0, 0);

        Button cancelButton = (Button) view.findViewById(R.id.dismiss);
        final Dialog finalDialog = dialog;
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalDialog.dismiss();
            }
        });

        return dialog;

    }

    public static AlertDialog emptyScheduleDialog(final Context context) {

        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_schedule, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        AlertDialog dialog = builder.create();

        dialog.setView(view, 0, 0, 0, 0);

        Button cancelButton = (Button) view.findViewById(R.id.dismiss);
        final Dialog finalDialog = dialog;
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalDialog.dismiss();
            }
        });
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.dont_show_suggestions);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SharedPreferencesUtil.showSuggestions(b);
            }
        });

        return dialog;

    }

    public static AlertDialog clearScheduleDialog(final Context context) {

        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_clear, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        AlertDialog dialog = builder.create();

        dialog.setView(view, 0, 0, 0, 0);

        Button cancelButton = (Button) view.findViewById(R.id.cancel);
        final Dialog finalDialog = dialog;
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalDialog.dismiss();
            }
        });
        Button clearButton = (Button) view.findViewById(R.id.clear);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalDialog.dismiss();
                HomeActivity.clearSchedule(context);
            }
        });

        return dialog;

    }

    public static AlertDialog updateDialog(final OfficialList schedule, final String update, final Context context) {
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.dialog_update, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        AlertDialog dialog = builder.create();

        dialog.setView(view, 0, 0, 0, 0);

        Button cancelButton = (Button) view.findViewById(R.id.no);
        final Dialog finalDialog = dialog;
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalDialog.dismiss();
            }
        });

        Button updateButton = (Button) view.findViewById(R.id.yes);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalDialog.dismiss();
                HomeActivity.performUpdate(schedule, update, context);
            }
        });

        return dialog;
    }

    public static AlertDialog syncSpeakersDialog(final Context context) {

        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_sync, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        AlertDialog dialog = builder.create();

        dialog.setView(view, 0, 0, 0, 0);

        Button cancelButton = (Button) view.findViewById(R.id.cancel);
        final Dialog finalDialog = dialog;
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalDialog.dismiss();
            }
        });
        Button clearButton = (Button) view.findViewById(R.id.sync);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalDialog.dismiss();
                HomeActivity.syncSchedule(context);
            }
        });

        return dialog;

    }

    public static Dialog shareScheduleDialog(final Context context) {

        AlertDialog.Builder b = new AlertDialog.Builder(context);
        b.setTitle(R.string.schedule_options);
        String[] types = {context.getString(R.string.schedule_share_save),context.getString(R.string.schedule_save)};
        b.setItems(types, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
                switch (which) {
                    case 0:
                        // export CSV and share
                        SchedulePagerFragment.backupDatabaseCSV();
                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_SUBJECT, R.string.schedule_share);
                        sendIntent.setType("text/csv");

                        Uri uri = Uri.fromFile(getOutputMediaFile());
                        sendIntent.putExtra(Intent.EXTRA_STREAM, uri);

                        context.startActivity(Intent.createChooser(sendIntent, context.getResources().getText(R.string.action_share)));
                        break;
                    case 1:
                        // export CSV
                        SchedulePagerFragment.backupDatabaseCSV();
                        Toast.makeText(context, context.getString(R.string.backup), Toast.LENGTH_SHORT).show();
                }
            }

        });

        Dialog dialog = b.create();
        dialog.setCanceledOnTouchOutside(true);

        return dialog;
    }

    public static DetailsFragment getDetailsDialog(Default item) {
        DetailsFragment detailsDialogFragment = DetailsFragment.newInstance(item);
        return detailsDialogFragment;
    }

    public static File getOutputMediaFile()
    {
        Context context = HackerTrackerApplication.getAppContext();

        // checks to see if the external storage SD card is mounted and has write access
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

            // creates a folder in the external storage SD card
            File mediaStorageDir = new File (Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS), context.getString(R.string.app_name));

            // create the storage directory if it does not exist
            if (!mediaStorageDir.exists()) {
                if (!mediaStorageDir.mkdirs()) {
                    Log.d(context.getString(R.string.app_name), context.getString(R.string.directory_fail));
                    return null;
                }
            }

            return new File(mediaStorageDir.getPath() + File.separator + context.getString(R.string.filename));
        } else {
            Toast.makeText(HackerTrackerApplication.getAppContext(), R.string.no_storage, Toast.LENGTH_LONG).show();
        }

        return null;
    }
}
