package br.com.ubook.desafioubook.mvp.model;

import android.util.Log;

import com.google.gson.Gson;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import br.com.ubook.desafioubook.domain.ResultMoviesList;
import br.com.ubook.desafioubook.mvp.presenter.PresenterListMovies;
import br.com.ubook.desafioubook.network.APINetwork;
import retrofit2.Callback;

public class ModelListMoviesTest {

    @Captor
    private  ArgumentCaptor<Callback<ResultMoviesList>> captor;
    @Mock
    private APINetwork.ListMovieRepositoryImpl repository;
    @Mock
    private PresenterListMovies presenterListMovies;

    private ModelListMovies model;

    private String json = "";


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        model = new ModelListMovies(repository, presenterListMovies);
        try {
            json = IOUtils.INSTANCE.readStringFromResourcePath("movies_result_request.json");
        }catch (Exception e){
            Log.e("TEST", "Erro load file");
        }

    }

    @Test
    public void checkNull(){
        Assert.assertNotNull(model.getRepository());
        Assert.assertNotNull(model.getPresenterListMovies());
    }

    @Test
    public void shouldRequestListMovies_Sucess(){
        Gson  gson = new Gson();
        final ResultMoviesList moviesList = gson.fromJson(json, ResultMoviesList.class);
        Mockito.doAnswer(new Answer<ResultMoviesList>() {
            @Override
            public ResultMoviesList answer(InvocationOnMock invocation) throws Throwable {
                presenterListMovies.requestListMoviesSucess(moviesList);
                return moviesList;
            }
        }).when(repository).request(captor.capture());
        model.requestListMovies();
        Mockito.verify(repository, Mockito.times(1)).request(captor.capture());
        Mockito.verify(presenterListMovies, Mockito.times(1)).requestListMoviesSucess(moviesList);

    }


    @Test
    public void shouldRequestListMovies_Error(){
          Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                presenterListMovies.requestListMoviesError();
                return null;
            }
        }).when(repository).request(captor.capture());
        model.requestListMovies();
        Mockito.verify(repository, Mockito.times(1)).request(captor.capture());
        Mockito.verify(presenterListMovies, Mockito.times(1)).requestListMoviesError();
    }

}
