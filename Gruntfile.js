module.exports = function (grunt) {
    var targetDir = 'target/sms_project/static/js/';
    grunt.initConfig({
            clean: {
                dev: [targetDir]
            },
            browserify: {
                options: {
                    transform: [ require('grunt-react').browserify]
                },
                client: {
                    src: ['public_react/jsx/react_components/**/*.jsx'],
                    dest: 'target/sms_project/static/js/app.built.js'
                }
            }
    });

    grunt.loadNpmTasks('grunt-browserify');
    grunt.loadNpmTasks('grunt-contrib-clean');
    grunt.loadNpmTasks('grunt-react');
    grunt.registerTask('build', 'Create javascript code ready to test', ['clean:dev', 'browserify']);
    grunt.registerTask('default', ['browserify']);
};