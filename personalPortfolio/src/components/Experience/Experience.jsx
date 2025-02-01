import React from "react";
import styles from "./Experience.module.css";
import experience from "../../data/experience.json";
import { getImageUrl } from "../../utils";

const Experience = () => {
  return (
    <section className={styles.container} id="experience">
      <h2 className={styles.title}>Experience</h2>

      <ul className={styles.history}>
        {experience.map((expItem, id) => {
          return (
            <li key={id} className={styles.historyItem}>
              <img
                src={getImageUrl(expItem.imageSrc)}
                alt={`${expItem.organisation} Logo`}
              />

              <div className={styles.historyItemDetails}>
                <h3>{`${expItem.role}, ${expItem.organisation}`}</h3>
                <p>{`${expItem.startDate} - ${expItem.endDate}`}</p>
                <ul>
                  {expItem.experiences.map((exp, id) => {
                    return <li key={id}>{exp}</li>;
                  })}
                </ul>
                <div className={styles.certificate}>
                  <a
                    href={expItem.certificate}
                    target="_blank"
                    rel="noopener noreferrer"
                  >
                    <img
                      src={getImageUrl("history/certificate.png")}
                      alt="certificate"
                    />
                    Certificate
                  </a>
                </div>
              </div>
            </li>
          );
        })}
      </ul>
    </section>
  );
};

export default Experience;
