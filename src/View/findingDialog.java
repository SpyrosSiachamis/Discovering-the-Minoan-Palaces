package src.View;

import src.model.findings.Fresco;
import src.model.pawns.Arch;
import src.model.pawns.Pawn;
import src.model.pawns.Thesseus;
import src.model.positions.FindingPosition;

import javax.swing.*;

/**
 * The {@code findingDialog} class is a custom dialog window that prompts the player to take action upon finding an ancient artifact.
 * The available options differ depending on the type of pawn (either {@link Arch} or {@link Thesseus}) and the type of finding (either {@link Fresco} or other artifacts).
 * The dialog offers choices to excavate, photograph, or destroy the finding if the pawn is an instance of {@link Thesseus}, with corresponding messages based on the player's decision.
 * <p>
 * <b>Pre-condition</b>: A pawn and a finding position must be provided to the constructor. The pawn’s type determines the actions available for the dialog.
 * <p>
 * <b>Post-condition</b>: The player is prompted with a dialog asking for their choice. The player's decision is stored and can be retrieved using the {@link #getChoice()} method.
 * <p>
 * <b>Invariant</b>: The dialog is always presented based on the type of finding and pawn. The user's decision is saved and accessible after interaction.
 */
public class findingDialog extends JOptionPane {
    private int choice = 0;
    /**
     * Constructs a dialog that asks the player whether to photograph, excavate, or destroy a finding, based on the type of pawn and the finding.
     * <p>
     * If the finding is a {@link Fresco} and the pawn is an {@link Arch}, the player is asked whether they want to photograph the finding.
     * If the pawn is a {@link Thesseus}, the player is asked whether they want to destroy the finding.
     * For non-Fresco finds, an excavate or destroy option is presented depending on the pawn's type.
     * <p>
     * <b>Post-condition</b>: The player’s decision is recorded and can be retrieved via the {@link #getChoice()} method.
     *
     * @param pawn The pawn interacting with the finding.
     * @param pos The position of the finding.
     */
    public findingDialog(Pawn pawn, FindingPosition pos) {
        Object[] options1 = {"Excavate", "Ignore"};
        Object[] options2 = {"Destroy", "Ignore"};
        if (pos.getFind() instanceof Fresco) {
            if (pawn instanceof Arch) {
                choice = showOptionDialog(null, "Do you want to photograph the finding?", "Ancient Artifact has been found!", YES_NO_OPTION, QUESTION_MESSAGE, null, options1, options1[1]);
                if (choice == YES_OPTION) {
                    showMessageDialog(null, "Finding has been photographed!");
                } else {
                    showMessageDialog(null, "Ignored...");
                }
            } else if (pawn instanceof Thesseus) {
                choice = showOptionDialog(null, "Do you want to destroy the finding?", "Ancient Artifact has been found!", YES_NO_OPTION, QUESTION_MESSAGE, null, options2, options2[1]);
                if (choice == YES_OPTION) {
                    showMessageDialog(null, "Finding has been destroyed!!");
                } else {
                    showMessageDialog(null, "Ignored...");
                }
            }
        } else {
            if (pawn instanceof Arch) {
                choice = showOptionDialog(null, "Do you want to excavate the finding?", "Ancient Artifact has been found!", YES_NO_OPTION, QUESTION_MESSAGE, null, options1, options1[1]);
                if (choice == YES_OPTION) {
                    showMessageDialog(null, "Finding has been excavated!");
                } else {
                    showMessageDialog(null, "Ignored...");
                }
            } else if (pawn instanceof Thesseus) {
                choice = showOptionDialog(null, "Do you want to destroy the finding?", "Ancient Artifact has been found!", YES_NO_OPTION, QUESTION_MESSAGE, null, options2, options2[1]);
                if (choice == YES_OPTION) {
                    showMessageDialog(null, "Finding has been destroyed!!");
                } else {
                    showMessageDialog(null, "Ignored...");
                }
            }
        }
    }

    /**
     * Returns the player's choice from the dialog.
     *
     * @return The choice made by the player: 1 for "Yes", 2 for "No", or "No" if no decision has been made.
     */
    public int getChoice() {
        return choice;
    }
}
