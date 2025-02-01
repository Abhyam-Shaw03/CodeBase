import React from "react";
import { Typewriter } from "react-simple-typewriter";
import styles from "./Hero.module.css";
import { getImageUrl } from "../../utils";

const Hero = () => {
  return (
    <section className={styles.container}>
      <div className={styles.content}>
        <h1 className={styles.title}>
          Hi, I'm
          <br />
          Abhyam Shaw
          <br />
          <span className={styles.typing}>
            <Typewriter
              words={["a Developer", "a Designer", "a Problem Solver"]}
              loop={true} // Loops infinitely
              cursor
              cursorStyle="|"
              typeSpeed={100}
              deleteSpeed={70}
              delaySpeed={1000}
            />
          </span>
        </h1>
        <p className={styles.description}>
          "Engineering Scalable Solutions | Optimizing Performance | Innovating
          with Code."
        </p>

        <div className={styles.buttonsContainer}>
          <a href="mailto:abhyamshaw3@gmail.com" className={styles.contactBtn}>
            Contact Me
          </a>

          <a
            href="/AbhyamShaw_Resume.pdf"
            download="AbhyamShaw_Resume.pdf"
            className={styles.downloadBtn}
          >
            Resume
          </a>
        </div>
      </div>
      <img
        src={getImageUrl("hero/ownpic.jpg")}
        alt="Hero image of me"
        className={styles.heroImg}
      />
      <div className={styles.topBlur} />
      <div className={styles.bottomBlur} />
    </section>
  );
};

export default Hero;
