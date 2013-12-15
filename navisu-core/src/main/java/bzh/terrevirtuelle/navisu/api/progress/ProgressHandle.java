package bzh.terrevirtuelle.navisu.api.progress;

/**
 * NaVisu
 *
 * @author tibus
 * @date 22/10/2013 21:18
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
     * Notify the user about progress by showing messageText with details.
     *
     * @param message
     */
    void	progress(String message);

    /**
     * Notify the user about completed workunits and show additional detailed messageText.
     *
     * @param message
     * @param workunit
     */
    void	progress(String message, int workunit);

    /**
     * Change the display titleText of the progress task.
     *
     * @param newDisplayName
     */
    void	setDisplayName(String newDisplayName);

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
     * Currently determinate task (with percentage or time estimate) can be switched to indeterminate mode.
     */
    void	switchToIndeterminate();
}
