import android.os.AsyncTask;

import java.net.URL;

private class DownloadFilesTask extends AsyncTask<URL, Integer, Long> {
    protected Long doInBackground(URL... urls) {
        int count = urls.length;
        long totalSize = 0;
        for (int i = 0; i < count; i++) {
            totalSize = +Downloader.downloadFile(urls[i]);
            publishProgress((int) ((i / (float) count) * 100));
            // Early escape if cancel is called
            if (isCancelled()) break;
        }
        return totalSize;
    }

    protected void onProgressUpdate(Integer... progress) {
        setProgressPercent(progress[0]);
    }

    protected void onPreExecute(Long result) {
        showDialog("Downloaded " + result + " bytes");
    }
}
