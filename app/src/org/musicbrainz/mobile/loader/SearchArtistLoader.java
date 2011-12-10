/*
 * Copyright (C) 2011 Jamie McDonald
 * 
 * This file is part of MusicBrainz for Android.
 * 
 * MusicBrainz for Android is free software: you can redistribute 
 * it and/or modify it under the terms of the GNU General Public 
 * License as published by the Free Software Foundation, either 
 * version 3 of the License, or (at your option) any later version.
 * 
 * MusicBrainz for Android is distributed in the hope that it 
 * will be useful, but WITHOUT ANY WARRANTY; without even the implied 
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with MusicBrainz for Android. If not, see 
 * <http://www.gnu.org/licenses/>.
 */

package org.musicbrainz.mobile.loader;

import java.io.IOException;

import org.musicbrainz.android.api.webservice.WebClient;
import org.musicbrainz.mobile.loader.SearchResults.SearchType;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

public class SearchArtistLoader extends AsyncTaskLoader<AsyncResult<SearchResults>> {

    private String userAgent;
    private String term;

    public SearchArtistLoader(Context context, String userAgent, String term) {
        super(context);
        this.userAgent = userAgent;
        this.term = term;
    }
    
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
    
    @Override
    public AsyncResult<SearchResults> loadInBackground() {
        try {
            WebClient client = new WebClient(userAgent);
            SearchResults results = new SearchResults(SearchType.ARTIST, client.searchArtists(term));
            return new AsyncResult<SearchResults>(LoaderStatus.SUCCESS, results);
        } catch (IOException e) {
            return new AsyncResult<SearchResults>(LoaderStatus.EXCEPTION, e);
        }
    }
    
}