//using the top function instead of the down one beacuse of some changes made in vite.config.js

// export const getImageUrl = (path) => {
//   const basePath = "/portfolio";
//   return `/assets/${path}`;
// };

// export const getImageUrl = (path) => {
//   return new URL(`/assets/${path}`, import.meta.url).href;
// };

export const getImageUrl = (path) => {
  return `${import.meta.env.BASE_URL}assets/${path}`;
};

/*
export const getImageUrl = (path) => {
  return new URL(`/assets/${path}`, import.meta.url).href;
};
*/
