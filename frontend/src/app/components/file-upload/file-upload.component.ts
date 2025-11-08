import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

import { FileUploadService } from '../../services/file-upload.service';
import { FileStatistics } from '../../models/file-statistics.model';

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.scss'],
  imports: [FormsModule, CommonModule],
  providers: [],
})
export class FileUploadComponent {
  fileNameDisplay: String = 'No file chosen';
  fileStatistics: FileStatistics | null = null;
  errorMessage: String = '';

  private selectedFile: File | null = null;

  constructor(private fileUploadService: FileUploadService) {}

  onUpload(event: any) {
    this.reset();
    const inputElement = event.target as HTMLInputElement;
    if (inputElement.files && inputElement.files.length > 0) {
      this.fileNameDisplay = inputElement.files[0].name;
      this.selectedFile = inputElement.files[0];
    }
  }

  onSubmit(event: Event) {
    event.preventDefault();
    if (this.selectedFile === null) {
      this.errorMessage = 'Please select a file.';
    } else {
      this.fileUploadService
        .uploadAndProcessFile(this.selectedFile)
        .then((response) => {
          if (response.data) {
            this.fileStatistics = response.data;
          }
          if (response.error) {
            this.errorMessage = String(response.error);
          }
        })
        .catch((error) => {});
    }
  }

  private reset() {
    this.fileStatistics = null;
    this.errorMessage = '';
  }
}
