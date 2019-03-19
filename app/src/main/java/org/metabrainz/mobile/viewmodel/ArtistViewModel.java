package org.metabrainz.mobile.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.metabrainz.mobile.api.data.ArtistWikiSummary;
import org.metabrainz.mobile.api.data.search.entity.Release;
import org.metabrainz.mobile.api.data.search.entity.Artist;
import org.metabrainz.mobile.repository.LookupRepository;
import org.metabrainz.mobile.util.SingleLiveEvent;

import java.util.List;

public class ArtistViewModel extends ViewModel {

    private LookupRepository repository = LookupRepository.getRepository();
    private MutableLiveData<Artist> artistData;
    private Artist artist;
    private SingleLiveEvent<ArtistWikiSummary> artistWiki;
    private MutableLiveData<List<Release>> releaseListLiveData;
    private String MBID;
    private boolean mbidHasChanged = true;

    public ArtistViewModel() {
    }

    public void setMBID(String MBID) {
        if (MBID != null && !MBID.isEmpty()) {
            this.MBID = MBID;
            mbidHasChanged = true;
        } else mbidHasChanged = false;
    }

    public MutableLiveData<Artist> initializeArtistData(){
        if (artistData == null)
            artistData = repository.initializeArtistData();
        return artistData;
    }

    public void getArtistData(){
        repository.getArtist(MBID);
    }

    public SingleLiveEvent<ArtistWikiSummary> getArtistWiki(String title, int method){
        if (artistWiki == null || mbidHasChanged)
            artistWiki = loadArtistWiki(title, method);
         return artistWiki;
    }

    public void fetchCoverArtForRelease(List<Release> releases, int position) {
        // Ask the repository to fetch the cover art and update ArtistData LiveData
        // Whoever is observing that LiveData, will receive the release with the cover art
        repository.fetchCoverArtForRelease(releases, position);
    }

    public MutableLiveData<List<Release>> initializeReleasesLiveData(){
        if (releaseListLiveData == null)
            releaseListLiveData = repository.initializeLiveData();
        return releaseListLiveData;
    }

    private SingleLiveEvent<ArtistWikiSummary> loadArtistWiki(String title, int method){
        return repository.getArtistWikiSummary(title ,method);
    }
}