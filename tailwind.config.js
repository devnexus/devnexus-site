// ./tailwind.config.js
import '@tailwindcss/typography';
/** @type {import('tailwindcss').Config} */
module.exports = {
  plugins: [
    require('@tailwindcss/typography')
  ],
  content: [
    "./_includes/**/*.html",
    "./_layouts/**/*.html",
    "./_posts/*.md",
    "./*.{html,md}",
    "./_organizers/*.html"
  ],
  // ...
};