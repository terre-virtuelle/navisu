package bzh.terrevirtuelle.navisu.api.progress;

/**
 * Created with IntelliJ IDEA.
 * User: tibus
 * Date: 22/10/13
 * Time: 21:18
 * To change this template use File | Settings | File Templates.
 */
public interface ProgressHandle {

    /**
     * Finish the task, remove the task's component from the progress bar UI.
     */
    void	finish();

    /**
     * Notify the user about completed workunits.
     * @param workunit
     */
    void	progress(int workunit);

    /**
     * Notify the user about progress by showing message with details.
     *
     * @param message
     */
    void	progress(String message);

    /**
     * Notify the user about completed workunits and show additional detailed message.
     *
     * @param message
     * @param workunit
     */
    void	progress(String message, int workunit);

    /**
     * Change the display name of the progress task.
     *
     * @param newDisplayName
     */
    void	setDisplayName(String newDisplayName);

    /**
     * Set a custom initial delay for the progress task to appear in the status bar.
     *
     * @param millis
     */
    void	setInitialDelay(int millis);

    /**
     * start the progress indication for indeterminate task.
     */
    void	start();

    /**
     * start the progress indication for a task with known number of steps.
     *
     * @param workunits
     */
    void	start(int workunits);

    /**
     * start the progress indication for a task with known number of steps and known time estimate for completing the task.
     *
     * @param workunits
     * @param estimate
     */
    void	start(int workunits, long estimate);

    /**
     * Current task can switch to silent suspend mode where the progress bar stops moving, hides completely or partially.
     *
     * @param message
     */
    void	suspend(String message);

    /**
     * Currently indeterminate task can be switched to show percentage completed.
     *
     * @param workunits
     */
    void	switchToDeterminate(int workunits);

    /**
     * Currently indeterminate task can be switched to show the time estimate til completion.
     *
     * @param workunits
     * @param estimate
     */
    void	switchToDeterminate(int workunits, long estimate);

    /**
     * Currently determinate task (with percentage or time estimate) can be switched to indeterminate mode.
     */
    void	switchToIndeterminate();
}
