To run specific test class with different parameters use this command:

```
mvn clean test -Dtest=ReplyToAndDeleteEmailTest -Dbrowser=chrome -Denvironment=prod
```

To run test suite with different parameters use this command:

```
mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/smoke-suite.xml -Denvironment=dev -Dbrowser=chrome
```

Available parameters:

- **environment: dev, prod**
- **suite: surefire.suiteXmlFiles=src/test/resources/smoke-suite.xml, surefire.suiteXmlFiles=src/test/resources/regression-suite.xml**
- **test: CreateSaveAndDeleteDraftEmailTest, MarkAsStarredEmailTest, ReplyToAndDeleteEmailTest**
- **browser: chrome, edge, firefox**

Scenario 1: Create and Delete a Draft Email
Steps:

1. Open browser and navigate to Gmail login page.

2. Enter valid abstractEmail address and password, then click login.

3. Click "Compose" to create a new abstractEmail.

4. Fill in addressee, subject, and body.

5. Close the compose window to save as a draft.

6. Navigate to "Drafts" folder.

7. Verify that the draft is present and subject is correct.

8. Open the draft abstractEmail.

9. Assert that the reply text is correct.

10. Delete the draft abstractEmail.

Scenario 2: Reply to an Email and Verify it in Sent Folder
Steps:

1. Open browser and navigate to Gmail login page.

2. Enter valid abstractEmail address and password, then click login.

3. Open the first abstractEmail from Inbox.

4. Click "Reply."

5. Enter reply text in the body.

6. Click "Send."

7. Navigate to "Sent" folder.

8. Assert that the reply abstractEmail is present and subject is correct.

9. Open the sent abstractEmail.

10. Assert that the reply text is correct.

11. Delete the abstractEmail.

Scenario 3: Mark an Email as Starred and Verify It
Steps:

1. Open browser and navigate to Gmail login page.

2. Enter valid abstractEmail address and password, then click login.

3. Open the first abstractEmail from Inbox.

4. Mark the abstractEmail as "Starred."

5. Navigate to the "Starred" label/folder.

6. Assert that the abstractEmail appears under "Starred."

7. Open the starred abstractEmail.

8. Assert that the abstractEmail is still starred.

9. unmark the abstractEmail.
