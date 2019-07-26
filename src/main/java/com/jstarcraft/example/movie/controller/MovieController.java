package com.jstarcraft.example.movie.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jstarcraft.example.common.output.NormalOutput;
import com.jstarcraft.example.movie.service.Movie;
import com.jstarcraft.example.movie.service.MovieService;

import io.swagger.annotations.ApiOperation;
import it.unimi.dsi.fastutil.objects.Object2FloatMap;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final static Logger logger = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieService movieService;

    /**
     * 获取推荐电影
     * 
     * @param userIndex
     * @param recommendKey
     * @return
     */
    @ApiOperation(value = "获取推荐电影", notes = "获取推荐电影")
    @GetMapping("/getRecommendMovies")
    public NormalOutput<List<MovieOutput>> getRecommendMovies(@RequestParam int userIndex, @RequestParam String recommendKey) {
        Object2FloatMap<Movie> movies = movieService.getRecommendMovies(userIndex, recommendKey);
        return new NormalOutput<>(MovieOutput.instanceOf(movies));
    }

    /**
     * 获取搜索电影
     * 
     * @param userIndex
     * @param searchKey
     * @return
     */
    @ApiOperation(value = "获取搜索电影", notes = "获取搜索电影")
    @GetMapping("/getSearchMovies")
    public NormalOutput<List<MovieOutput>> getSearchMovies(@RequestParam int userIndex, @RequestParam String searchKey) throws Exception {
        Object2FloatMap<Movie> movies = movieService.getSearchMovies(userIndex, searchKey);
        return new NormalOutput<>(MovieOutput.instanceOf(movies));
    }

}
