import React, { useRef } from "react";
import styles from "./Contact.module.css";
import { getImageUrl } from "../../utils";
import emailjs from "@emailjs/browser";

const ContactUs = () => {
  const form = useRef();

  const sendEmail = (e) => {
    e.preventDefault();

    emailjs
      .sendForm("service_sbmojbu", "template_o7ivoif", form.current, {
        publicKey: "3CtndrMocQETF7Dp_",
      })
      .then(
        () => {
          console.log("SUCCESS!");
          form.current.reset();
          alert("Your message has been sent successfully!");
        },
        (error) => {
          console.log("FAILED...", error.text);
          alert("Oops! Something went wrong. Please try again.");
        }
      );
  };

  return (
    <footer className={styles.container} id="contact">
      <div className={styles.content}>
        <div className={styles.greet}>
          <h2>
            Contact
            <br />
            Me
          </h2>
          <p>Feel free to reach out.!</p>
        </div>
        <div className={styles.formContainer}>
          <form ref={form} onSubmit={sendEmail} className={styles.form}>
            <label>Name</label>
            <input type="text" name="from_name" placeholder="Enter your name" />
            <label>Email</label>
            <input
              type="email"
              name="from_email"
              placeholder="Enter your email"
            />
            <label>Phone</label>
            <input
              type="number"
              name="from_phone"
              placeholder="Enter your phone"
            />
            <label>Place</label>
            <input
              type="text"
              name="from_place"
              placeholder="Enter your place"
            />
            <label>Message</label>
            <textarea
              name="message"
              placeholder="Hi..!!  Enter the message you wnat to convey..!!"
            />
            <button className={styles.button} type="submit">
              Send
            </button>
          </form>
        </div>
      </div>
      <ul className={styles.social}>
        <li className={styles.socialItem}>
          <a href="https://github.com/Abhyam-Shaw" target="_blank">
            <img src={getImageUrl("contact/githubIcon.png")} alt="Github" />
          </a>
        </li>
        <li className={styles.socialItem}>
          <a
            href="https://www.linkedin.com/in/abhyam-shaw-ba30a4237/"
            target="_blank"
          >
            <img src={getImageUrl("contact/linkedinIcon.png")} alt="LinkedIn" />
          </a>
        </li>
        <li className={styles.socialItem}>
          <a href="mailto:abhyamshaw3@gmail.com" target="_blank">
            <img src={getImageUrl("contact/emailIcon.png")} alt="Email" />
          </a>
        </li>
        <li className={styles.socialItem}>
          <a href="tel:+918768640833" target="_blank">
            <img src={getImageUrl("contact/phoneIcon.png")} alt="Phone" />
          </a>
        </li>
      </ul>
    </footer>
  );
};

export default ContactUs;
