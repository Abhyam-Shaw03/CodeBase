import React, { useEffect, useRef } from "react";
import styles from "./About.module.css";
import { getImageUrl } from "../../utils";
import VanillaTilt from "vanilla-tilt";

const About = () => {
  const imageRef = useRef(null);

  useEffect(() => {
    if (imageRef.current) {
      VanillaTilt.init(imageRef.current, {
        max: 25,
        speed: 400,
        glare: true,
        "max-glare": 0.5,
      });
    }
  }, []);

  return (
    <section className={styles.container} id="about">
      <h2 className={styles.title}>About Me</h2>
      <div className={styles.content}>
        <img
          ref={imageRef}
          src={getImageUrl("about/ownpic.jpg")}
          alt="me"
          className={styles.aboutImage}
        />
        <p className={styles.aboutItem}>
          I am a highly motivated 4th-year BTech student specializing in
          Artificial Intelligence and Machine Learning at Asansol Engineering
          College. With a robust foundation in Java, C++, Object-Oriented
          Programming (OOPs), Data Structures & Algorithms (DSA). I am skilled
          in designing and implementing scalable systems.
          <br />
          <br />
          I possess hands-on expertise in Java technologies such as JDBC and
          Hibernate, as well as proficiency in front-end web development (HTML5,
          CSS, JavaScript) and Database Management System (DBMS) with MySQL. My
          technical toolkit is complemented by experience with industry-standard
          development tools, including IntelliJ IDEA and VSCode.
          <br />
          <br />
          Having completed two internships, including advanced training in Java
          at Wipro, I am well-versed in developing solutions in Agile
          environments. I am passionate about leveraging technology to solve
          complex problems and eager to contribute to innovative projects that
          drive organizational success.
        </p>
      </div>
    </section>
  );
};

export default About;
