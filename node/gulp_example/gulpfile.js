const gulp = require('gulp')
const sourcemap = require('gulp-sourcemaps')
const babel = require('gulp-babel')
const concat = require('gulp-babel')

gulp.task('default', () => {
    return gulp.src('app/*.jsx')
        .pipe(sourcemap.init())
        .pipe(babel({
            presets: ['es2015', 'react']
        }))
        .pipe(concat('all.js'))
        .pipe(sourcemap.write('.'))
        .pipe(gulp.dest('dist'))
})
