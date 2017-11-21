Organization of the SCSS folder is a bit confusing because it covers
various attempts at clean up that were abandoned in order to get the
site working in time.

wro/all.scss
Generated via sass and wro process in the original site.  All legacy styles
exist here.  I had tried to re-organize but found many legacy styles that
I could not get working after refactoring out to pure scss.

devnexus.scss
A new beginning, as additional styles are needed they are developed here
using 'proper' scss syntax.

archeology
Collection of the various source files that make up all.scss  so that someday
we can refactor to logical scss components.

bootstrap, fontawesome
core libraries used in developing the sites styles.
