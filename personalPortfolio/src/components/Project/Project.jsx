// import React from "react";
// import styles from "./Project.module.css";
// import projects from "../../data/projects.json";
// import { ProjectCard } from "./ProjectCard";

// const Project = () => {
//   return (
//     <section className={styles.container} id="projects">
//       <h2 className={styles.title}>Projects</h2>
//       <div className={styles.projects}>
//         {projects.map((project, id) => {
//           return <ProjectCard key={id} project={project} />;
//         })}
//       </div>
//     </section>
//   );
// };

// export default Project;

import React from "react";
import styles from "./Project.module.css";
import projects from "../../data/projects.json";
import { ProjectCard } from "./ProjectCard";
import { Swiper, SwiperSlide } from "swiper/react";
import { Navigation, Pagination, Autoplay } from "swiper/modules";
import "swiper/css";
import "swiper/css/navigation";
import "swiper/css/pagination";

const Project = () => {
  return (
    <section className={styles.container} id="projects">
      <h2 className={styles.title}>Projects</h2>
      <Swiper
        modules={[Navigation, Pagination, Autoplay]}
        spaceBetween={20}
        slidesPerView={1}
        navigation
        pagination={{ clickable: true }}
        autoplay={{ delay: 2500, disableOnInteraction: false }}
        breakpoints={{
          640: { slidesPerView: 1 },
          768: { slidesPerView: 2 },
          1024: { slidesPerView: 3 },
        }}
        className={styles.swiper}
      >
        {projects.map((project, id) => (
          <SwiperSlide key={id}>
            <ProjectCard project={project} />
          </SwiperSlide>
        ))}
      </Swiper>
    </section>
  );
};

export default Project;
