import styles from "./App.module.css";
import About from "./components/About/About";
import ContactUs from "./components/Contact/ContactUs";
import Experience from "./components/Experience/Experience";
import Hero from "./components/Hero/Hero";
import Navbar from "./components/Navbar/Navbar";
import Project from "./components/Project/Project";
import Skills from "./components/Skills/Skills";

function App() {
  return (
    <div className={styles.App}>
      <Navbar />
      <Hero />
      <About />
      <Skills />
      <Experience />
      <Project />
      <ContactUs />
    </div>
  );
}

export default App;
