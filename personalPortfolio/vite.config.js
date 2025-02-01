import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  css: {
    modules: {
      localsConvention: "camelCase",
    },
  },

  server: {
    fs: {
      allow: [".."], // Allow access to parent directory
    },
  },
  resolve: {
    alias: {
      "@assets": "/assets", // Optional alias for easier imports
    },
  },
  base: "/",
});
