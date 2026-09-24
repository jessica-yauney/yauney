---
layout: post
title: Dancing into Coding Class
subtitle: What ballet class has taught me about teaching computer science?
tags: [education, computer-science, pedagogy, learning-sciences, dance]
comments: true
author: Jessica Yauney
---

## Dancing into Coding Class
*What ballet class has taught me about teaching computer science?*

Students are stretching on the ground or at the bar as they chat before class starts. The instructor turns on music and the class all moves to the edges of the class ready to complete the same pliers that they do every week. Now that they are warm, the teacher shows a short warm up first tendus, then ronde jambes, and pasees before pirouettes, at the bar before moving into the center. 
Students try it, laugh at the wobble, try again. A few minutes later, the group does a quick showing—nothing perfect, but undeniably better. The energy is part concentration, part play. That’s the studio. And it’s a surprisingly good blueprint for how we might teach computer science.

---

## Why dance is a powerful lens for CS

Both dance and programming combine many micro-skills into one fluid performance: balance, timing, and phrasing in the studio; decomposition, control flow, and testing in the IDE. The value of dance, pedagogically, is that it **makes process visible**. Rehearsal is normal. Mistakes are data. Performances create an authentic audience and a reason to polish work.

**Key idea in plain language:**

* **Modeling (social learning)**: We get better by watching someone competent and borrowing their strategies [1].
* **Cognitive apprenticeship**: Teachers reveal expert thinking, coach while we try, give temporary supports (*scaffolds*), then remove them (*fading*) so we own the skill [2].
* **Deliberate practice**: Short, targeted reps with quick feedback—not just “doing more,” but doing what improves the weakness [3].

---

## Principle 1 — Modeling: “I show, we try, you own it”

**Define the terms:**

* **Modeling:** The teacher performs a task while *thinking aloud*—speaking decisions that are usually invisible.
* **Scaffolding:** Temporary supports (checklists, hints, templates) that make a slightly-too-hard task doable; removed as skill grows.
* **Fading:** Gradually removing those supports so independence increases [2].

**How it looks in CS (dance → code):**

* **Demo a phrase:** Live-code a tiny program (e.g., count words) and narrate: *“Loop enters, condition checks, accumulator updates.”*
* **Mark the movement:** Run the same example again but leave strategic blanks for students to fill.
* **Spotlight micro-skills:** One pass only on naming; one only on control flow; one only on tests.

{: .box-note}
**Tip:** Avoid the silent “perfect pass.” Model small stumbles and recoveries: *“Test failed—great, now we read the error and isolate the branch.”* This normalizes iteration and shows expert debugging moves [2].

---

## Principle 2 — Simple practice: “Phrase work” before full routines

**Define the terms (plain English):**

* **Deliberate practice:** Focused reps targeting one sub-skill with immediate feedback [3].
* **Retrieval practice:** Look away from notes and reconstruct from memory; it strengthens recall.
* **Interleaving:** Mix problem types (loops *and* conditionals) to build flexible skill.

**Studio-inspired patterns:**

* **Across-the-floor → REPL reps (5 minutes):** Write one clean `for` loop; next rep, vary input; next rep, add a test.
* **Call-and-response:** Instructor provides a failing test; students write the minimal code to pass.
* **Tempo changes:** Solve the same logic as a 3-line snippet, a 15-line function, and a 30-line script.

**Feedback that fits the moment:** tiny checklists (e.g., *“named variables clearly,” “wrote one failing test first”*), quick peer checks, and a spaced revisit next class.

---

## Principle 3 — Performance: Public, authentic, integrative tasks

**Define the terms:**

* **Authentic task:** Something that could exist outside class (a tiny CLI tool, a plot for a club, a data story).
* **Integration:** Combining multiple micro-skills into one coherent artifact.

**Dance → CS translations:**

* **Studio showing → Demo day:** 90-second live demos to peers/guests; short Q&A about trade-offs.
* **Choreography remix → Code remix:** Fork a starter app and add one expressive “phrase” (feature).
* **Dress rehearsal:** Timed run on test data with a designated “tech crew” (peers) to surface deployment issues.

**Assessment that supports growth:** Use a rubric with *Functionality, Readability, Tests, UX clarity,* plus a brief **artist’s statement** describing intent and trade-offs (part of cognitive apprenticeship’s *articulation & reflection*) [2].

---

## Principle 4 — No expectation of perfection early: Normalize rehearsal

**Define the terms:**

* **ZPD (Zone of Proximal Development):** The sweet spot where tasks are just beyond current ability but doable with support [4].
* **Formative assessment:** Feedback during learning that guides the *next step* rather than judging the *final product* [5].
* **Productive struggle:** Challenging work that stretches but doesn’t overwhelm.

**Studio-inspired practices:**

* **Rough-cut critiques:** Submit a draft repo (even with stubs). Instructor and peers give targeted notes.
* **Two-minute fix protocol:** Pick one bug or refactor you can ship in 2 minutes; share what changed and why.
* **Reframe errors:** Treat failing tests as *rehearsal notes*, not verdicts.

---

## Principle 5 — Personal expression & style: Technique serves voice

**Define the terms:**

* **Agency:** Ownership over choices—tools, aesthetics, problem framing.
* **Universal Design for Learning (UDL):** Offer multiple ways to engage, represent ideas, and show learning (e.g., CLI, notebook, web demo).

**Studio-inspired practices:**

* **Choice within constraints:** “Build any two-category data viz with one interaction.”
* **Artist’s statement:** 150 words on intention and trade-offs.
* **Remix:** Convert a peer’s CLI into a small web app; compare design choices.

**Assessment note:** Separate correctness from expression. Reward distinctive solutions that still meet the spec.

---

## Anticipating objections (and quick replies)

* **“This will take too much time.”** Keep drills to 10–15 minutes; use lightning demos; reuse a simple, transparent rubric.
* **“Students fear public demos.”** Start with opt-in gallery walks or pair demos; build to live presentations.
* **“Grading gets subjective.”** Publish examples, specs, and the rubric upfront; add self- and peer-assessment checkpoints.
* **“Accessibility and equity?”** Provide multiple exemplars, captions/alt-text, and alternative modalities (video, write-up, live).

---

## Mini-glossary (plain English)

* **Modeling:** The teacher demonstrates a task while explaining choices (think-aloud).
* **Cognitive apprenticeship:** Structure learning as *model → coach → scaffold → fade → reflect* so novices see expert thinking [2].
* **Deliberate practice:** Short reps targeting a specific weakness with quick feedback [3].
* **Retrieval practice:** Recalling from memory rather than rereading.
* **Interleaving:** Mixing problem types during practice to build flexibility.
* **ZPD & scaffolding:** Supports that make a slightly-too-hard task doable; removed over time [4].
* **Formative assessment:** Feedback during learning that points to the next step, not just a grade [5].
* **Authentic task:** A product or performance that would make sense outside class.

---

## Closing

Teach CS like a studio: **model**, **rehearse**, **perform**, **reflect**. Start small—try a 5-minute REPL drill tomorrow or a 90-second demo day next week. The promise of the studio mindset is simple: when we normalize rehearsal, make thinking visible, and invite style, students don’t just pass—they **perform**.

---

### References

[1] Bandura, A. (1977). *Social Learning Theory.*
[2] Collins, A., Brown, J. S., & Newman, S. E. (1989). Cognitive apprenticeship. In *Knowing, Learning, and Instruction*, 453–494.
[3] Ericsson, K. A., Krampe, R. T., & Tesch-Römer, C. (1993). Deliberate practice. *Psychological Review, 100*(3), 363–406.
[4] Vygotsky, L. S. (1978). *Mind in Society.*
[5] Black, P., & Wiliam, D. (1998). Assessment and classroom learning. *Assessment in Education, 5*(1), 7–74.

---

# Draft for Section 8 — A Two-Week Module (Arrays & Loops)

**Day 1 — Modeling + Phrase Work**

* Live-code counting elements in a list; narrate decisions (think-aloud).
* REPL reps: indexing (3 reps), slicing (3), simple `for` loops (3).
* Exit ticket (retrieval): define *index*, *slice*, *loop invariant* in your own words.

**Day 2 — Spotlight Micro-skills**

* “Marking” demo: instructor runs the same task with blanks; students fill them.
* Peer mirror: one types, one narrates intent and checks invariants.
* Quick formative check: 3 autograded tests; instructor skims failures for pattern.

**Day 3 — Interleaving & Call-and-Response**

* Mixed drills: filter then map then reduce-by-hand walkthroughs.
* Instructor supplies failing tests; students write minimal code to pass.
* Two-minute fixes: identify one naming or boundary bug and ship a commit.

**Day 4 — Scale & Tempo Changes**

* Same logic at 3-line, 15-line, and 30-line versions.
* Mini-reflection: what changed about your approach as the program grew?

**Day 5 — Mini-Performance (Lightning Demos)**

* 60-second demo of a tiny data-cleaning tool.
* Critique circle using TAG (Tell one strength, Ask a question, Give a suggestion).

**Day 6 — Remix & Extend**

* Add file I/O and a small plot; turn a notebook into a script.
* UDL options: CLI, notebook, or web micro-app.

**Day 7 — Dress Rehearsal**

* Timed run with a fresh dataset; assign “tech crew” peers to each group.
* Collect “rehearsal notes” (failing tests, UX friction) as issues.

**Day 8 — Performance Day**

* Public demo (invite another class).
* Short artist’s statement: intent, trade-offs, one lesson learned.

**Rubric Sketch (share on Day 1):** Functionality (40), Readability (20), Tests (20), UX clarity (10), Artist’s statement (10).
**Equity Guardrails:** Multiple exemplars; captions/alt-text; alternative submission formats.

---

# Alternate Draft for Section 10 — Anticipating Objections

**“We don’t have time.”**
Use *micro-structures*: 5-minute REPL drills, 60–90 second demos, one-page rubrics. Keep performances small but frequent.

**“Students are anxious about public work.”**
Start with pair demos and gallery walks before live presentations. Allow prerecorded demos; make at least one showcase opt-in.

**“This seems subjective to grade.”**
Separate *spec compliance* (tests pass, outputs correct) from *craft* (readability, UX). Publish the rubric and exemplars on day one; include self- and peer-assessment.

**“What about accessibility and equity?”**
Offer UDL choices (CLI/notebook/web). Provide captions, alt-text, and multiple input/output modalities. Ensure feedback is about *intent, clarity, and impact*, not background knowledge.

**“Will this scale?”**
Yes—lean on templates, autograding for specs, and peer roles (tech crew, narrator, reviewer). Reuse the same performance format each unit to reduce overhead.
